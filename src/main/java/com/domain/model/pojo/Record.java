/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.domain.model.pojo;

import java.util.Objects;

/**
 *
 * @author Utente
 */
//DATA: data in formato ISO 8601
//HASH: info_hash codicato in esadecimale
//TOPIC: ID numerico relativo alla discussione (forum TNTVillage)
//POST: ID numerico relativo al messaggio (forum TNTVillage)
//AUTORE: nome utente dell'autore della release
//TITOLO: titolo della release
//DESCRIZIONE: metadati relativi alla release
//DIMENSIONE: dimensione complessiva dei file della release (in byte)
//CATEGORIA: ID numerico relativo alla categoria della release

public class Record {

	private String date, hash, title, description, category;

	public Record(String date, String hash, String title, String description, String category) {
		this.date = date;
		this.hash = hash;
		this.title = title;
		this.description = description;
		this.category = category;
	}

	public Record() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Record{" + " HASH = " + hash + " --- TITOLO = " + title + " --- DESCRIZIONE = " + description
				+ " --- categoria=" + category + '}';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.date);
		hash = 97 * hash + Objects.hashCode(this.hash);
		hash = 97 * hash + Objects.hashCode(this.title);
		hash = 97 * hash + Objects.hashCode(this.description);
		hash = 97 * hash + Objects.hashCode(this.category);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Record other = (Record) obj;
		if (!Objects.equals(this.date, other.date)) {
			return false;
		}
		if (!Objects.equals(this.hash, other.hash)) {
			return false;
		}
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.description, other.description)) {
			return false;
		}
		if (!Objects.equals(this.category, other.category)) {
			return false;
		}
		return true;
	}

}
