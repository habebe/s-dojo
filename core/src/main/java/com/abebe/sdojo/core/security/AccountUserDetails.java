package com.abebe.sdojo.core.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abebe.sdojo.core.models.entities.account.Account;

public class AccountUserDetails implements UserDetails
{
	private static final long serialVersionUID = -719472296242196949L;
	private final Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		  GrantedAuthority authority = new GrantedAuthority() {
	       private static final long serialVersionUID = 1L;
				@Override
	            public String getAuthority() {
	                return "USER";
	            }
	        };
	        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(authority);
	        return authorities;
	}

	@Override
	public String getPassword() {
		return this.getAccount().getPassword();
	}

	@Override
	public String getUsername() {
		if(this.getAccount().getUserName() != null)
			return this.getAccount().getUserName();
		return this.getAccount().getEmail();
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private Account getAccount() {
		return account;
	}
}
