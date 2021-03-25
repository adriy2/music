package fr.pgah.java.son;

/*
 * @(#)MidiSynth.java 1.15
 *
 * Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use, modify and
 * redistribute this software in source and binary code form, provided that i) this copyright notice
 * and license appear on all copies of the software; and ii) Licensee does not utilize the software
 * in a manner which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL EXPRESS OR IMPLIED
 * CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE
 * USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of aircraft, air traffic,
 * aircraft navigation or aircraft communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Map;

public class MidiSynth {

  private Synthesizer synthesizer;
  private Instrument instruments[];
  private DonneesChannel channels[];

  public DonneesChannel getSpecialisedChannel(int index) {
    return channels[index];
  }

  // prepares instruments, channels for playback
  public void open() {
    getSynthesizer();
    setupInstruments();
    setupChannels();
  }

  // synthesizes sound given instrument, note, and velocity
  public void play(int instrument, int note, int velocity) {
    DonneesChannel channelData = getChannelData(instrument);
    MidiChannel midiChannel = channelData.getChannel();
    midiChannel.noteOn(note, velocity);
  }

  // stops playback of the given instrument
  public void stop(int instrument, int note) {
    DonneesChannel channelData = getChannelData(instrument);
    MidiChannel midiChannel = channelData.getChannel();
    midiChannel.noteOff(note, 0);
  }

  // sets up the channels for this MidiSynth
  private void setupChannels() {
    MidiChannel midiChannels[] = synthesizer.getChannels();
    channels = new DonneesChannel[midiChannels.length];
    for (int i = 0; i < channels.length; i++) {
      channels[i] = new DonneesChannel(midiChannels[i], i);
    }
  }

  // populates this with a variety of instruments
  private void setupInstruments() {
    if (getSoundBank() != null) {
      instruments = synthesizer.getDefaultSoundbank().getInstruments();
      synthesizer.loadInstrument(instruments[0]);
    }
  }

  // gets the soundbank from the synthesizer
  private Soundbank getSoundBank() {
    return synthesizer.getDefaultSoundbank();
  }

  private void getSynthesizer() {
    try {
      if (synthesizer == null) {
        if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
          System.out.println("getSynthesizer() failed!");
          return;
        }
      }
      synthesizer.open();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // returns the channel associated with the given instrument, stored in HashMap
  private DonneesChannel getChannelData(int instrument) {
    Map<Integer, DonneesChannel> channelMap = new HashMap<Integer, DonneesChannel>();
    DonneesChannel channelData = channelMap.get(instrument);
    if (channelData == null) {
      channelData = getSpecialisedChannel(channelMap.size());
      MidiChannel midiChannel = channelData.getChannel();
      midiChannel.programChange(instrument);
      channelMap.put(instrument, channelData);
    }
    return channelData;
  }
}
