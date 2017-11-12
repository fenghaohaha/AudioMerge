package com.haofeng.merge;

import java.util.ArrayList;

import javax.sound.sampled.AudioFileFormat;

public class MergeAudio {
	public static void main(String[] args) {
		String wavFile1 = "1.wav";
		String wavFile2 = "2.wav";
		String wavFile3 = "3.wav";
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(wavFile1);
		list.add(wavFile2);
		list.add(wavFile3);
		
		AudioUtils.MergeAudioFile(list, "D:\\wavAppended.wav" , AudioFileFormat.Type.WAVE);
	}
}
