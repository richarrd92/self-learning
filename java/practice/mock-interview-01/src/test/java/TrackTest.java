import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import technical.Track;

public class TrackTest {

    @Test
    void constructorWithTitleAndDurationTest() {
        Track testTrack = new Track("TestTrack", 2.01);
        assertEquals("TestTrack", testTrack.getTrackTitle());
        assertEquals(2.01, testTrack.getDuration());
    }

    @Test
    void constructorWithTitleDurationAndArtist() {
        Track testTrack = new Track("TestTrack", 2.01, "Artist1");
        assertEquals("TestTrack", testTrack.getTrackTitle());
        assertEquals(2.01, testTrack.getDuration());
        assertEquals("Artist1", testTrack.getTrackArtist());
    }

    @Test
    void getTitleTest() {
        Track testTrack = new Track("TestTrack", 2.01);
        assertEquals("TestTrack", testTrack.getTrackTitle());
    }

    @Test
    void setTitleTest() {
        Track testTrack = new Track("TestTrack", 2.01);
        testTrack.setTrackTitle("Track1");
        assertEquals("Track1", testTrack.getTrackTitle());
    }

    @Test
    void getDurationTest() {
        Track testTrack = new Track("TestTrack", 2.01);
        assertEquals(2.01, testTrack.getDuration());
    }

    @Test
    void setDurationTest() {
        Track testTrack = new Track("TestTrack", 2.01);
        assertEquals(2.01, testTrack.getDuration());
        testTrack.setDuration(1.00);
        assertEquals(1.00, testTrack.getDuration());
    }

    @Test
    void getArtistTest() {
        Track testTrack = new Track("TestTrack", 2.01, "Artist");
        assertEquals("Artist", testTrack.getTrackArtist());
    }

    @Test
    void setArtistTest() {
        Track testTrack = new Track("TestTrack", 2.01, "Artist");
        assertEquals("Artist", testTrack.getTrackArtist());
        testTrack.setTrackArtist("New Artist");
        assertEquals("New Artist", testTrack.getTrackArtist());
    }

}
