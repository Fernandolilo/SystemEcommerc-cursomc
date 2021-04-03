package com.systempro.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.systempro.cursomc.domain.enums.Perfil;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> autorities;
	
	public UserSS()
	{
		
		
	}
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.autorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	public Integer getId() {
		return id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return autorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	//trata se conta esta expirada
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
	//trata se conta bloqueada
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//trata credencial se expirada ou ativa 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//usuario ativo
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
