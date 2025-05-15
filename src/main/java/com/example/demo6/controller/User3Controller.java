package com.example.demo6.controller;

import com.example.demo6.dto.User3Dto;
import com.example.demo6.entity.User3;
import com.example.demo6.service.User3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class User3Controller {

    private final User3Service user3Service;

//    public User3Controller(User3Service user3Service) {
//        this.user3Service = user3Service;
//    }

    @GetMapping("/userList")
    public String userListGet(Model model) {
        List<User3> userList = user3Service.getUserList();
        System.out.println("=========================>> userList : " + userList);
        model.addAttribute("userList", userList);
        return "user/userList";
    }

    @GetMapping("/userInput")
    public String userInputGet() {
        return "user/userInput";
    }

    @PostMapping("/userInput")
    public String userInputPost(RedirectAttributes rttr, Model model, User3Dto dto) {
        // 아이디 중복체크

        Optional<User3> opUser3 = user3Service.getUserIdSearch(dto.getMid());
        if(!opUser3.isEmpty()) {
            System.out.println("===============>>opUser3 : " + opUser3);
            model.addAttribute("message", "아이디가 중복되었습니다.");
            return "user/userInput";
        }

        // 중복처리후 회원가입처리한다.
        User3 user3 = User3.createUser(dto);
        user3Service.setUserInput(user3);
        rttr.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "redirect:/user/userList";
    }


    @GetMapping("/userDelete")
    public String userDeleteGet(RedirectAttributes rttr, Long id) {
        user3Service.setUserDelete(id);
        rttr.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/user/userList";
    }

    // 개별검색 / 수정을 위한 검색처리
    @PostMapping("/userIdCheck")
    public String userIdCheckPost(Model model, String mid, String searchFlag) {
        if(searchFlag.equals("s")) {
            List<User3> userList = user3Service.getUserMidSearch(mid);
            model.addAttribute("userList", userList);
            return "user/userList";
        }
        else {
            // 아이디 중복체크
            Optional<User3> opUser = user3Service.getUserIdSearch(mid);
            if(opUser.isEmpty()) {
                return "redirect:/";
            }
            // 중복처리후 회원가입처리한다.
            User3Dto dto = User3Dto.createUser(opUser);
            model.addAttribute("dto", dto);
            return "user/userUpdate";
        }



    }
    // 수정하기
    @PostMapping("/userUpdateOk")
    public String userUpdatePost(RedirectAttributes rttr, User3Dto dto) {
        User3 user3 = User3.createUser(dto);
        user3Service.setUserUpdate(user3);
        rttr.addFlashAttribute("message", "수정이 완료되었습니다.");
        return "redirect:/user/userList";
    }



}
