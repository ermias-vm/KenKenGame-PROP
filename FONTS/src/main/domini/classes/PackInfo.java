package main.domini.classes;

public class PackInfo { //PackInfo d'Usuari + Temps per a RankingPerTipus
	private String user;
	private Double temps;
	private String id;
	
	public PackInfo(){
	}
	
	public PackInfo(String user, Double temps, String id){ 
		this.user=user;
		this.temps=temps;
		this.id=id;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Double getTemps() {
		return temps;
	}
	public void setTemps(Double temps) {
		this.temps = temps;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	
	
}
