/*************************************************************
*
*
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
#include "Assignment4.h"
using namespace std;
/*************************************************************
*
*
*
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

	DSWaveSignal = readWaveFile((char*)DSFilePath, DSWaveSignal);
	DSWaveSignalSize = myWave.dataSize;
	IRWaveSignal = readWaveFile((char*)IRFilePath, IRWaveSignal);
	IRWaveSignalSize = myWave.dataSize;
	OutputWaveSignalSize = DSWaveSignalSize + IRWaveSignalSize - 1;
	OutputWaveSignal = new float[OutputWaveSignalSize];

	cout << "Starting Convolution..." << endl;
	convolve(DSWaveSignal, DSWaveSignalSize, IRWaveSignal, IRWaveSignalSize, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Convolution Complete \n" << endl;
	writeWaveFile((char*)OutputFilePath, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Time Elapsed: " << (clock() - startClock) / (double)(CLOCKS_PER_SEC / 1000) << endl;

	return 0;
}
/*************************************************************
*
*
*
**************************************************************/
float* readWaveFile(char *inputFilePath, float *inputSignal) {
	ifstream inputfile(inputFilePath, ios::in | ios::binary);

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
	
	return inputSignal;
}
/*************************************************************
*
*
*
**************************************************************/
void writeWaveFile(char* outputFilePath, float* outputSignal, int outputSignalSize) {
	ofstream outputfile(outputFilePath, ios::out | ios::binary);
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

	for (int i = 0; i < outputSignalSize; i++){
		var = (int16_t)(outputSignal[i] * (pow(2.0, 15.0) - 1));
		outputfile.write((char*)&var, 2);
	}

	outputfile.close();
}
/*************************************************************
*
*
*
**************************************************************/
float* convolve(float x[], int N, float h[], int M, float y[], int P) {
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
