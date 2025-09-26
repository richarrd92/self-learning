package technical;

public class Track {

	private String trackTitle;
	private double duration;
	private String trackArtist;

	/**
	 * Constructs a Track assigning values to the trackTitle and duration fields
	 *
	 * @param trackTitle   the title to assign to the trackTitle field
	 * @param duration     the duration to assign to the duration field
	 */
	public Track(String trackTitle, double duration) {
		this.trackTitle = trackTitle;
		this.duration = duration;
	}

	/**
	 * Constructs a Track assigning values to the trackTitle, duration, and artist fields.
	 *
	 * @param trackTitle   the title to assign to the trackTitle field
	 * @param duration     the duration to assign to the duration field
	 * @param artist       the artist to assign to the artist field
	 */
	public Track(String trackTitle, double duration, String artist) {
		this.trackTitle = trackTitle;
		this.duration = duration;
		this.trackArtist = artist;
	}

	/**
	 * Getter method for the title of the track.
	 *
	 * @return the title of the track
	 */
	public String getTrackTitle() {
		return trackTitle;
	}

	/**
	 * Setter method for the title of the track.
	 *
	 * @param trackTitle the new title of the track
	 */
	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	/**
	 * Getter method for the duration (in minutes) of the track.
	 *
	 * @return the duration (in minutes) of the track
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Setter method for the duration (in minutes) of the track.
	 *
	 * @param duration the new duration (in minutes) of the track
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Getter method for the artist of the track.
	 *
	 * @return the artist for the track
	 */
	public String getTrackArtist() {
		return trackArtist;
	}

	/**
	 * Setter method for the artist of the track.
	 *
	 * @param artist the new artist of the track
	 */
	public void setTrackArtist(String artist) {
		this.trackArtist = artist;
	}

}
