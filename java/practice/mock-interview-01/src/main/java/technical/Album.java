package technical;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import technical.exceptions.MissingImplementationException;

public class Album {

    private String albumName;
    private String albumArtist;
    private List<Track> trackList;

    /**
     * Constructs an Album and assigns values to the artist and albumName fields.
     * This constructor should also initialize the trackList field to be an empty ArrayList. 
     *
     * @param artist   the artist to assign to the albumArtist field
     * @param albumName     the name to assign to the albumName field
     */
    public Album(String artist, String albumName) {
        this.albumArtist = artist;
        this.albumName = albumName;
        this.trackList = new ArrayList<>();
    }

    /**
     * Constructs an Album and assigns values to the artist, tracks, and albumName fields.
     * This constructor should also set each track's trackArtist field to match this album's 
     * artist field.
     *
     * @param artist the artist to assign to the albumArtist field
     * @param tracks   the tracks to assign to the trackList field
     * @param albumName   the name to assign to the albumName field
     */
    public Album(String artist, List<Track> tracks, String albumName) {
    	this.albumArtist = artist;
        this.albumName = albumName;
        for(Track track : tracks){
            track.setTrackArtist(artist);
        }
        this.trackList = tracks;
    }

    /**
     * Getter method for the name of an album
     *
     * @return the name of the album
     */
    public String getAlbumName() {
    	return this.albumName;
    }

    /**
     * Setter method for the name of the album
     *
     * @param albumName the new name of the album
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Getter method for the artist of an album.
     *
     * @return the artist of the album
     */
    public String getAlbumArtist() {
    	return this.albumArtist;
    }

    /**
     * Setter method for the albumArtist of the album.
     *
     * @param artist the new artist of the album
     */
    public void setAlbumArtist(String artist) {
        this.albumArtist = artist;
    }

    /**
     * Getter method for the trackList on an album.
     * 
     * IMPORTANT: This should return a defensive copy.
     *
     * @return a list of tracks on the album
     */
    public List<Track> getTrackList() {
        return new ArrayList<>(this.trackList);
    }

    /**
     * Calculate the total duration of the album by summing the duration of each track on this
     * album.
     *
     * @return the sum of each individual track's length on this album
     */
    public double getAlbumDuration() {
    	double total_duration = 0;
        for(Track track : getTrackList()){
            total_duration += track.getDuration();
        }

        return total_duration;
    }

    /**
     * This method should add the provided track to this album's trackList.
     * It should not add null values to the list.
     * It should also set the track's trackArtist to match this album's albumArtist.
     * If the addition was successful, return true, otherwise, return false.
     *
     * @param track the track to add to the tracks list
     */
    public void addTrack(Track track) {
        if (track == null) {
            return; // don't add null
        }

        // make sure artist is consistent
        track.setTrackArtist(this.albumArtist);

        // prevent duplicates
        if (this.trackList.contains(track)) {
            return;
        }

        // add to list
        this.trackList.add(track);
    }

    /**
     * This method should remove the provided track from this album.
     * It should also set the track's artist to null if the provided track was in the
     * tracks list.
     * If the removal was successful, return true, otherwise, return false.
     *
     * @param track the track to remove from the tracks list
     */
    public void removeTrack(Track track) {
        if (track == null || !trackList.contains(track)) return;
        this.trackList.remove(track);
    }

    /**
     * BONUS: This method should directly sort the trackList based on the rule string passed in:
     * 	- If rule is "duration" then directly sort the trackList in ascending order by their duration(in minutes)
     * 	- If rule is "title" then directly sort the trackList in alphabetical order based on their trackTitle
     * Otherwise don't apply any sort to the tracks list
     * 
     * IMPORTANT: This method should not modify the album's trackList field
     *
     * @param rule the rule string to determine the sort
     * @return the trackLsist after the appropriate sort is applied
     */
    public List<Track> sortBy(String rule) {
        // copy of trackList so we don’t modify the original
        List<Track> sortedTrackList = new ArrayList<>(this.trackList);

        // sort by duration
        if ("duration".equalsIgnoreCase(rule)) {
            sortedTrackList.sort(Comparator.comparing(Track::getDuration));
        }

        // sort by title
        else if ("title".equalsIgnoreCase(rule)) {
            sortedTrackList.sort(Comparator.comparing(Track::getTrackTitle));
        }

        // else -> do nothing, leave as is
        return sortedTrackList;
    }

}
