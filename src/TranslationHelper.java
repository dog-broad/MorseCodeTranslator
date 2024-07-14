import javax.sound.sampled.*;

public class TranslationHelper {
    private static volatile boolean stopPlaying = false;

    private static float pitch = 1000f; // Default pitch in Hz
    private static int dotDuration = 200; // Duration of a dot in ms
    private static float volume = 0.5f; // Volume as a percentage

    public static void playMorseSound(String morse) {
        stopPlaying = false;
        for (char c : morse.toCharArray()) {
            if (stopPlaying) break;
            if (c == '.') {
                playDot();
            } else if (c == '-') {
                playDash();
            } else {
                try {
                    Thread.sleep(dotDuration * 3L); // Space between letters
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void stopPlaying() {
        stopPlaying = true;
    }

    public static void setPitch(float pitch) {
        TranslationHelper.pitch = pitch;
    }

    public static void setDotDuration(int duration) {
        dotDuration = duration;
    }

    public static void setVolume(float volume) {
        TranslationHelper.volume = volume;
    }

    private static void playDot() {
        playTone(pitch, dotDuration);
    }

    private static void playDash() {
        playTone(pitch, dotDuration * 3);
    }

    private static void playTone(float hz, int msecs) {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(8000f, 8, 1, true, false);
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8 && !stopPlaying; i++) {
                double angle = i / (8000f / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 100 * volume);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable");
            System.out.println(e.getMessage());
        }
    }
}
