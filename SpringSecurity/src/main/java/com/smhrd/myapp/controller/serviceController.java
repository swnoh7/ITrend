package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.myapp.entity.Member;
import com.smhrd.myapp.mapper.MemberRepository;
import com.smhrd.myapp.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class serviceController {
	
	@Autowired
	private MemberRepository mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/dashboard")
    public String dashboard(Model model) {
        // 인증된 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        // 사용자의 ID를 가져옵니다.
        String userId = authentication.getName();
        // 사용자의 권한을 가져옵니다.
        String role = authentication.getAuthorities().toString();
        // Model에 사용자의 ID와 권한을 추가합니다.
        model.addAttribute("userId", userId);
        model.addAttribute("role", role);

        return "dashboard";
    }
	
	@PostMapping("/logout")
	public String logoutProcess(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@PostMapping("/login-process")
	public String loginProcess(MemberVO vo , HttpSession session) {
		Member entity = mapper.findByUseridAndPw(vo.getUserid(),vo.getPw());
		if(entity != null) {
			MemberVO myData = new MemberVO(entity.getUserid(), entity.getPw(),entity.getRole());
			//attribute 쓰고싶으면 HttpSession session 대신에 RedirectAttributes rttr 이거 집어넣으면됨
			//attribute를 사용할경우 myData안에 있는값 하나하나 설정해서 보내줘야함
			//rttr.addAttribute("userid", myData.getUserid());
			//rttr.addAttribute("role", myData.getRole());
			System.out.println("세션에 뭐가들어가니?? >> "+myData);
			session.setAttribute("myData", myData);
			return "redirect:/dashboard";
		}else {
			return "redirect:/login";
		}
		// *주의할 점 : 들어가는 value의 형태를 String으로 변환가능한 형태로 넣어주기
		// JSP파일에서 받아줄때는 ${param.myData}이렇게 받아올수 있음.
		// redirect:/mypage?myData=이나몰
		// /login 페이지 유효
		// /mypage 페이지 유효
		// 요청2번 응답2번 
	}
	
	@PostMapping("/join-process")
	public String joinProcess(MemberVO vo) {
		Member entity = new Member(vo.getUserid(), vo.getPw(), "admin", encoder);
		mapper.save(entity);
		return "login";
	}
}
