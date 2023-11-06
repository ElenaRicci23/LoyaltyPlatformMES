package 2_iterazione;

public class Cliente extends Utente {

	private string nome;
	private string cognome;
	private string email;

	public string getNome() {
		return this.nome;
	}

	public void setNome(string nome) {
		this.nome = nome;
	}

	public string getCognome() {
		return this.cognome;
	}

	public void setCognome(string cognome) {
		this.cognome = cognome;
	}

	public string getEmail() {
		return this.email;
	}

	public void setEmail(string email) {
		this.email = email;
	}

}