package com.ssafy.project.EmotionPlanet.Dto;

public class ApiDto {
	double acousticness;
	String analysis_url;
	double danceability;
	int duration_ms;
	double energy;
	String id;
	double instrumentalness;
	int key;
	double liveness;
	double loudness;
	int mode;
	double speechiness;
	double tempo;
	int time_signature;
	String track_href;
	String type;
	String uri;
	double valence;
	public double getAcousticness() {
		return acousticness;
	}
	public void setAcousticness(double acousticness) {
		this.acousticness = acousticness;
	}
	public String getAnalysis_url() {
		return analysis_url;
	}
	public void setAnalysis_url(String analysis_url) {
		this.analysis_url = analysis_url;
	}
	public double getDanceability() {
		return danceability;
	}
	public void setDanceability(double danceability) {
		this.danceability = danceability;
	}
	public int getDuration_ms() {
		return duration_ms;
	}
	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getInstrumentalness() {
		return instrumentalness;
	}
	public void setInstrumentalness(double instrumentalness) {
		this.instrumentalness = instrumentalness;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public double getLiveness() {
		return liveness;
	}
	public void setLiveness(double liveness) {
		this.liveness = liveness;
	}
	public double getLoudness() {
		return loudness;
	}
	public void setLoudness(double loudness) {
		this.loudness = loudness;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public double getSpeechiness() {
		return speechiness;
	}
	public void setSpeechiness(double speechiness) {
		this.speechiness = speechiness;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public int getTime_signature() {
		return time_signature;
	}
	public void setTime_signature(int time_signature) {
		this.time_signature = time_signature;
	}
	public String getTrack_href() {
		return track_href;
	}
	public void setTrack_href(String track_href) {
		this.track_href = track_href;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public double getValence() {
		return valence;
	}
	public void setValence(double valence) {
		this.valence = valence;
	}
	@Override
	public String toString() {
		return "ApiDto [acousticness=" + acousticness + ", analysis_url=" + analysis_url + ", danceability="
				+ danceability + ", duration_ms=" + duration_ms + ", energy=" + energy + ", id=" + id
				+ ", instrumentalness=" + instrumentalness + ", key=" + key + ", liveness=" + liveness + ", loudness="
				+ loudness + ", mode=" + mode + ", speechiness=" + speechiness + ", tempo=" + tempo
				+ ", time_signature=" + time_signature + ", track_href=" + track_href + ", type=" + type + ", uri="
				+ uri + ", valence=" + valence + "]";
	}
	
	
}
