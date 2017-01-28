package com.inigo.domotik.utils;
import java.util.UUID;

public class SHA256 {
	private String salt = "";
	private String hash = "";
	
	private String generateRandomString(){
		return UUID.randomUUID().toString();
	}
	
	public SHA256 generateSalt(){
		salt = generateRandomString();
		return this;
	}
	
	public SHA256 hash(String word){
		if (word != null){
			this.hash =  org.apache.commons.codec.digest.DigestUtils.sha256Hex(word+salt);
		}
		return this;
	}
	
	public boolean isValidHash(String hashed, String pass){
		hash(pass);
		System.out.println("Hash  "+ hashed +" is equals to " + hash + "? "+ hashed == null ? false : hashed.equals(this.hash));
		return hashed == null ? false : hashed.equals(this.hash);
	}

	public String getSalt() {
		return salt;
	}

	public SHA256 setSalt(String salt) {
		this.salt = salt;
		return this;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
