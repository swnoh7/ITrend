package com.smhrd.myapp.entity;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	
	public Member(String userid, String pw, String role, PasswordEncoder encoder) {
		this.userid = userid;
		this.pw = encoder.encode(pw);
		this.role = role;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx; // pk
	
	@Column(nullable = false)
	private String userid; // not null
	@Column(nullable = false)
	private String pw; // not null
	@Column(updatable = false, nullable = false)
	private String role; // 역할, 권한 설정 때 사용 예정
						 // default 값 'user'
	
	
}
