package com.capstone.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.trip.config.auth.PrincipalDetail;
import com.capstone.trip.domain.user.UserRepository;
import com.capstone.trip.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
public class UserController {

	private UserRepository userRepository;
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 회원가입 페이지
	 */
	@GetMapping("/auth/user/save")
	public String userSave() {
		return "layout/user/user-save";
	}

	/**
	 * 로그인 페이지
	 */
	@GetMapping("/auth/user/login")
	public String userLogin() {
		return "layout/user/user-login";
	}

	/**
	 * 회원수정 페이지
	 */
	@GetMapping("/user/mypage/update")
	public String userUpdate(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
		model.addAttribute("principal", principalDetail.getUser());
		return "layout/user/user-update";
	}

	@GetMapping("/user/mypage/information")
	public String userInformation(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
		model.addAttribute("principal", principalDetail.getUser());
		return "layout/user/user-information";
	}

	@GetMapping({"/user", "/user/mypage"})
	public String myPage() {
		return "layout/user/user-information";
	}

	@GetMapping("/user/username/exists")
	public ResponseEntity<String> checkUsernameDuplicate(@RequestParam(value = "username") String username) {
		boolean isDuplicate = userService.checkUsernameDuplicate(username);
		if (isDuplicate) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용할 수 없는 아이디입니다.");
		} else {
			return ResponseEntity.ok("사용할 수 있는 아이디입니다.");
		}
	}

	@GetMapping("/user/email/exists")
	public ResponseEntity<String> checkEmailDuplicate(@RequestParam(value = "email") String email) {
		boolean isDuplicate = userService.checkEmailDuplicate(email);
		if (isDuplicate) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용할 수 없는 이메일입니다.");
		} else {
			return ResponseEntity.ok("사용할 수 있는 이메일입니다.");
		}
	}

	@GetMapping("/user/nickname/exists")
	public ResponseEntity<String> checkNicknameDuplicate(@RequestParam(value = "nickname") String nickname) {
		boolean isDuplicate = userService.checkNicknameDuplicate(nickname);
		if (isDuplicate) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용할 수 없는 닉네임입니다.");
		} else {
			return ResponseEntity.ok("사용할 수 있는 닉네임입니다.");
		}
	}
}
