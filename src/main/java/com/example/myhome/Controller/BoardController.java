package com.example.myhome.Controller;

import com.example.myhome.model.Board;
import com.example.myhome.repository.BoardRepository;
import com.example.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")//board 페이지와 매핑
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list") //게시판 화면에서 board에 있는 모든 데이터 가지고옴.
    public String list(Model model){
        List<Board> boards = boardRepository.findAll(); //리포지토리 사용해서 board의 모든 데이터를 가지고옴
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form") //form 화면에서
    public String form(Model model, @RequestParam(required = false) Long id){ //required = false의미: 필수가 아님. 필요한 경우에만 id값을 사용함.
        if(id == null)  {
            model.addAttribute("board", new Board());
        }
        else{
            Board board = boardRepository.findById(id).orElse(null); //id값을 검색 후 없을 경우 null 반환
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String boardSubmit(@Validated Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult); //유효값 검증
        boardRepository.save(board);
        return "redirect:/board/list"; //redirect키워드 주는 이유: return할때 키값(attribute)를 반환해야 하는데 해당 동작이 없으므로 포워딩.
                                        // -> 다시 list로 돌아가 list()함수 호출됨.
    }
}
