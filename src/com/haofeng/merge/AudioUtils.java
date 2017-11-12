package com.haofeng.merge;

import java.io.File;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioUtils {

	/**
	 * 合并多个音频文件
	 * 
	 * @param srcFilePaths
	 * @param objPath
	 * @param audioType
	 */
	public static void MergeAudioFile(List<String> srcFilePaths, String objPath, Type audioType) {

		ArrayList<AudioInputStream> audioInputStreams = new ArrayList<AudioInputStream>();
		AudioFormat audioFormat = null;
		AudioInputStream appendedFiles;
		SequenceInputStream sequenceInputStream;

		try {
			int listsize = srcFilePaths.size();
			long fileLength = 0;

			for (int i = 0; i < listsize; i++) {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(srcFilePaths.get(i)));
				audioInputStreams.add(audioInputStream);
				fileLength += audioInputStream.getFrameLength();
			}
			audioFormat = audioInputStreams.get(0).getFormat();

			Enumeration audioStreams = new Enumeration<InputStream>() {

				@Override
				public boolean hasMoreElements() {
					return !audioInputStreams.isEmpty();
				}

				@Override
				public InputStream nextElement() {
					return audioInputStreams.remove(0);
				}
			};

			sequenceInputStream = new SequenceInputStream(audioStreams);
			
			appendedFiles = new AudioInputStream(sequenceInputStream, audioFormat, fileLength);
			
			AudioSystem.write(appendedFiles, audioType, new File(objPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}