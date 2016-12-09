/*************************************************************
* Author:     Cumar Yusuf
* Course:     CPSC - 501
* Date:	      Dec 09 2016
* Email:      Cumar.Yusuf@ucalgary.ca
* Instructor: Leonard Manzara
* Purpose:	  This program simply does a time convolusion
*			  by taking in two wav file and an output file.
*			  The program reads the input files, does the
*			  convolution and then writes in the output file.
* Other:	  This program only deals with .wav files and
*			  deals with one channel
*
* Reference:  Instructor Manzara Notes
*	(Used above resource for the convolve function and many other)
*		      https://www.youtube.com/watch?v=4Y-G30OyPKw 
*	(Used above resource for how to read and write wave)
*			  https://github.com/charlescote/CPSC501-4
*   (Used above resource to figure out how to convert 
*			wave data to signal)
*			  http://stackoverflow.com/ 
*	(Used above resource for file manipulations and general question)
*		 	  http://www.cplusplus.com/
*	(Used above reference for data structures and read and write stuff)
*
**************************************************************/
#include "stdafx.h"
#include <windows.h>
#include <mmsystem.h>
#include <iostream>
#include <string>
#include <fstream>
#include <ctime>
#include <math.h>
#include <stdint.h>
#include "Assignment4_Base.h"
using namespace std;
/*************************************************************
* Purpose: The main function gets the input and output
*			file paths, calls the read function to read
*			the input files, calls the convolve function
*			that does the convolusion and then calls
*			the write function that rights the output
*			to the output file path. All of this is times.
**************************************************************/
int main(int argc, char* argv[]) {
	const char* DSFilePath;
	const char* IRFilePath;
	const char* OutputFilePath;
	float* DSWaveSignal = NULL;
	float* IRWaveSignal = NULL;
	float* OutputWaveSignal = NULL;
	int DSWaveSignalSize;
	int IRWaveSignalSize;
	int OutputWaveSignalSize;
	string DSInput;
	string IRInput;
	string Output;
	clock_t startClock;

	startClock = clock();
	cout << "Starting BaseLine Program.... " << endl;

	if (argc <= 1) {
		cout << "Input DrySound wave file name: ";
		cin >> DSInput;
		cin.get();
		DSFilePath = DSInput.c_str();

		cout << "Input IR wave file name: ";
		cin >> IRInput;
		cin.get();
		IRFilePath = IRInput.c_str();

		cout << "Give Output wave file name: ";
		cin >> Output;
		cin.get();
		OutputFilePath = Output.c_str();
	}
	else {
		DSFilePath = argv[1];
		cout << "Input  DrySound wave file name: " << DSFilePath << endl;

		IRFilePath = argv[2];
		cout << "Input IR wave file name: " << IRFilePath << endl;

		OutputFilePath = argv[3];
		cout << "Output wave file name: " << OutputFilePath << endl;
	}

	DSWaveSignal = baseReadWaveFile((char*)DSFilePath, DSWaveSignal);
	DSWaveSignalSize = myWave.dataSize;
	IRWaveSignal = baseReadWaveFile((char*)IRFilePath, IRWaveSignal);
	IRWaveSignalSize = myWave.dataSize;
	OutputWaveSignalSize = DSWaveSignalSize + IRWaveSignalSize - 1;
	OutputWaveSignal = new float[OutputWaveSignalSize];

	cout << "Starting Convolution..." << endl;
	baseConvolve(DSWaveSignal, DSWaveSignalSize, IRWaveSignal, IRWaveSignalSize, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Convolution Complete \n" << endl;
	baseWriteWaveFile((char*)OutputFilePath, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Time Elapsed: " << (clock() - startClock) / (double)(CLOCKS_PER_SEC / 1000) << endl;

	return 0;
}
/*************************************************************
* Purpose: The function simply gets the file path and calls
*			the build in fstream class to parse through the
*			file. This function put the data in the struct
*			data structure located in the .h file.
*			The data potion of the file is convert to signals
*			-1 to +1 instead of -2^15 to +2^15.
**************************************************************/
float* baseReadWaveFile(char *inputFilePath, float *inputSignal) {
	ifstream inputfile(inputFilePath, ios::in | ios::binary);
	if (inputfile.is_open()) {
		inputfile.seekg(ios::beg);

		inputfile.read(myWave.RIFF, 4);
		inputfile.read((char*)&myWave.ChunkSize, 4);
		inputfile.read(myWave.WAVE, 4);
		inputfile.read(myWave.FMT, 4);
		inputfile.read((char*)&myWave.subChunk1Size, 4);
		inputfile.read((char*)&myWave.audioFormat, 2);
		inputfile.read((char*)&myWave.numChannels, 2);
		inputfile.read((char*)&myWave.sampleRate, 4);
		inputfile.read((char*)&myWave.byteRate, 4);
		inputfile.read((char*)&myWave.blockAlign, 2);
		inputfile.read((char*)&myWave.bitsPerSample, 2);

		if (myWave.subChunk1Size == 18) {
			inputfile.seekg(2, ios::cur);
		}

		inputfile.read(myWave.subChunk2ID, 4);
		inputfile.read((char*)&myWave.subChunk2Size, 4);
		myWave.dataSize = myWave.subChunk2Size / 2;

		short *data = new short[myWave.dataSize];
		for (int i = 0; i < myWave.dataSize; i++) {
			inputfile.read((char *)&data[i], 2);
		}

		inputfile.close();

		short val;
		inputSignal = new float[myWave.dataSize];

		for (int i = 0; i < myWave.dataSize; i++) {
			val = data[i];
			inputSignal[i] = (val * 1.0) / (pow(2.0, 15.0) - 1);
			if (inputSignal[i] < -1.0)
				inputSignal[i] = -1.0;
		}
		myWave.signal = inputSignal;
	}
	else {
		cout << "File could not be opened: " << inputFilePath << endl;
		exit(EXIT_FAILURE);
	}

	return inputSignal;
}
/*************************************************************
*	Purpose: This function is the last function called in the
*			 program and simply created a .wav file using the
*			 output file path provided. The signal data is 
*			 converted back to .wav data before it's written
*			 to the file.
**************************************************************/
void baseWriteWaveFile(char* outputFilePath, float* outputSignal, int outputSignalSize) {
	ofstream outputfile(outputFilePath, ios::out | ios::binary);

	if (!outputfile.is_open()) {
		cout << "File could not be opened: " << outputFilePath << endl;
		exit(EXIT_FAILURE);
	}

	uint16_t var;

	char* RIFF = "RIFF";
	outputfile.write(RIFF, 4);
	myWave.ChunkSize = myWave.subChunk2Size + 36;
	outputfile.write((char*)&myWave.ChunkSize, 4);
	char* WAVE = "WAVE";
	outputfile.write(WAVE, 4);
	outputfile.write(myWave.FMT, 4);
	myWave.subChunk1Size = 16;
	outputfile.write((char*)&myWave.subChunk1Size, 4);
	outputfile.write((char*)&myWave.audioFormat, 2);
	outputfile.write((char*)&myWave.numChannels, 2);
	outputfile.write((char*)&myWave.sampleRate, 4);
	outputfile.write((char*)&myWave.byteRate, 4);
	outputfile.write((char*)&myWave.blockAlign, 2);
	outputfile.write((char*)&myWave.bitsPerSample, 2);
	outputfile.write(myWave.subChunk2ID, 4);
	myWave.subChunk2Size = myWave.numChannels * outputSignalSize * (myWave.bitsPerSample / 8);
	outputfile.write((char*)&myWave.subChunk2Size, 4);

	for (int i = 0; i < outputSignalSize; i++) {
		var = (int16_t)(outputSignal[i] * (pow(2.0, 15.0) - 1));
		outputfile.write((char*)&var, 2);
	}

	outputfile.close();
}

/*************************************************************
*	Purpose: This function does the convolution for us
**************************************************************/
float* baseConvolve(float x[], int N, float h[], int M, float y[], int P) {
	int n, m;

	for (n = 0; n < P; n++)
		y[n] = 0.0;

	for (n = 0; n < N; n++) {
		for (m = 0; m < M; m++){
			y[n + m] += x[n] * h[m];
		}
	}
	return y;
}
