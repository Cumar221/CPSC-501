/*************************************************************
* Author:	  Cumar Yusuf
* Course:	  CPSC - 501
* Date:		  Dec 09 2016
* Email:	  Cumar.Yusuf@ucalgary.ca
* Instructor: Leonard Manzara
*
* Purpose:    This program does the FFT Convolution Reverb
*			  by taking two input drysound and impluse response
*			  with an output destinaion. The program convolutes
*			  the drysound with the impulse response and writes
*			  the result into a output wav file.
* Other:	  This program only works for .wav files and in
*			  only one channel.
*
* Reference:  Instructor Manzara Notes
	(Used for the FFT function and eveything else)
*		      https://www.youtube.com/watch?v=4Y-G30OyPKw 
*	(Used above resource for how to read and write wave)
*			  https://github.com/charlescote/CPSC501-4
*   (Used above resource to figure out how do FFT Convolve portion)
*			  http://stackoverflow.com/
*	(Used above resource for file manipulations and general question)
*		 	  http://www.cplusplus.com/
*	(Used above reference for data structures and read and write stuff)
*			  http://www.thegeekstuff.com/2015/01/c-cpp-code-optimization
*	(Used above resource for best optimization practices
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
#include "Assignment4_FFT.h"
using namespace std;
/*************************************************************
*	Purpose: The main function gets the input and output
*			 file paths, calls the read function to read
*			 the input files, calls the convolve function
*			 that does the convolusion and then calls
*			 the write function that rights the output
*			 to the output file path. All of this is times.
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
	cout << "Starting FFT Program.... " << endl;

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

	DSWaveSignal = FFTReadWaveFile((char*)DSFilePath, DSWaveSignal);
	DSWaveSignalSize = FFTMyWave.dataSize;
	IRWaveSignal = FFTReadWaveFile((char*)IRFilePath, IRWaveSignal);
	IRWaveSignalSize = FFTMyWave.dataSize;
	OutputWaveSignalSize = DSWaveSignalSize + IRWaveSignalSize - 1;
	OutputWaveSignal = new float[OutputWaveSignalSize];

	cout << "Starting Convolution..." << endl;
	FFTConvolve(DSWaveSignal, DSWaveSignalSize, IRWaveSignal, IRWaveSignalSize, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Convolution Complete \n" << endl;
	FFTWriteWaveFile((char*)OutputFilePath, OutputWaveSignal, OutputWaveSignalSize);
	cout << "Time Elapsed: " << (clock() - startClock) / (double)(CLOCKS_PER_SEC / 1000) << endl;

	return 0;
}
/*************************************************************
*  Purpose: The function simply gets the file path and calls
*			the build in fstream class to parse through the
*			file. This function put the data in the struct
*			data structure located in the .h file.
*			The data potion of the file is convert to signals
*			-1 to +1 instead of -2^15 to +2^15.
**************************************************************/
float* FFTReadWaveFile(char *inputFilePath, float *inputSignal) {
	ifstream inputfile(inputFilePath, ios::in | ios::binary);
	if (inputfile.is_open()) {
		inputfile.seekg(ios::beg);

		inputfile.read(FFTMyWave.RIFF, 4);
		inputfile.read((char*)&FFTMyWave.ChunkSize, 4);
		inputfile.read(FFTMyWave.WAVE, 4);
		inputfile.read(FFTMyWave.FMT, 4);
		inputfile.read((char*)&FFTMyWave.subChunk1Size, 4);
		inputfile.read((char*)&FFTMyWave.audioFormat, 2);
		inputfile.read((char*)&FFTMyWave.numChannels, 2);
		inputfile.read((char*)&FFTMyWave.sampleRate, 4);
		inputfile.read((char*)&FFTMyWave.byteRate, 4);
		inputfile.read((char*)&FFTMyWave.blockAlign, 2);
		inputfile.read((char*)&FFTMyWave.bitsPerSample, 2);

		if (FFTMyWave.subChunk1Size == 18) {
			inputfile.seekg(2, ios::cur);
		}

		inputfile.read(FFTMyWave.subChunk2ID, 4);
		inputfile.read((char*)&FFTMyWave.subChunk2Size, 4);
		FFTMyWave.dataSize = FFTMyWave.subChunk2Size / 2;

		short *data = new short[FFTMyWave.dataSize];
		for (int i = 0; i < FFTMyWave.dataSize; i+=2) {
			inputfile.read((char *)&data[i], 2);
			inputfile.read((char *)&data[i + 1], 2);
		}

		inputfile.close();

		short val;
		inputSignal = new float[FFTMyWave.dataSize];

		for (int i = 0; i < FFTMyWave.dataSize; i+=2) {
			val = data[i];
			inputSignal[i] = ((val * 1.0) / 32768.0 - 1);
			if (inputSignal[i] < -1.0)
				inputSignal[i] = -1.0;
			val = data[i + 1];
			inputSignal[i + 1] = ((val * 1.0) / 32768.0 - 1);
			if (inputSignal[i + 1] < -1.0)
				inputSignal[i + 1] = -1.0;
		}
		FFTMyWave.signal = inputSignal;
	}
	else {
		cout << "File could not be opened: " << inputFilePath << endl;
		exit(EXIT_FAILURE);
	}
	
	return inputSignal;
}
/*************************************************************
*   Purpose: This function is the last function called in the
*			 program and simply created a .wav file using the
*			 output file path provided. The signal data is 
*			 converted back to .wav data before it's written
*			 to the file.
**************************************************************/
void FFTWriteWaveFile(char* outputFilePath, float* outputSignal, int outputSignalSize) {
	ofstream outputfile(outputFilePath, ios::out | ios::binary);

	if (!outputfile.is_open()) {
		cout << "File could not be opened: " << outputFilePath << endl;
		exit(EXIT_FAILURE);
	}
	uint16_t var;

	char* RIFF = "RIFF";
	outputfile.write(RIFF, 4);
	FFTMyWave.ChunkSize = FFTMyWave.subChunk2Size + 36;
	outputfile.write((char*)&FFTMyWave.ChunkSize, 4);
	char* WAVE = "WAVE";
	outputfile.write(WAVE, 4);
	outputfile.write(FFTMyWave.FMT, 4);
	FFTMyWave.subChunk1Size = 16;
	outputfile.write((char*)&FFTMyWave.subChunk1Size, 4);
	outputfile.write((char*)&FFTMyWave.audioFormat, 2);
	outputfile.write((char*)&FFTMyWave.numChannels, 2);
	outputfile.write((char*)&FFTMyWave.sampleRate, 4);
	outputfile.write((char*)&FFTMyWave.byteRate, 4);
	outputfile.write((char*)&FFTMyWave.blockAlign, 2);
	outputfile.write((char*)&FFTMyWave.bitsPerSample, 2);
	outputfile.write(FFTMyWave.subChunk2ID, 4);
	FFTMyWave.subChunk2Size = FFTMyWave.numChannels * outputSignalSize * (FFTMyWave.bitsPerSample / 8);
	outputfile.write((char*)&FFTMyWave.subChunk2Size, 4);

	for (int i = 0; i < outputSignalSize; i+=2) {
		var = (int16_t)(outputSignal[i] * 32768.0 - 1);
		outputfile.write((char*)&var, 2);
		var = (int16_t)(outputSignal[i+1] * 32768.0 - 1);
		outputfile.write((char*)&var, 2);
	}

	outputfile.close();
}
/*************************************************************
* Purpose: This is the function that convolves the data
**************************************************************/
void FFT(float data[], int nn, int isign){
	cout << "INN FFT....." << endl;
	unsigned long n, mmax, m, j, istep, i;
	float wtemp, wr, wpr, wpi, wi, theta;
	float tempr, tempi;

	n = nn << 1;
	j = 1;

	for (i = 1; i < n; i += 2) {
		if (j > i) {
			SWAP(data[j], data[i]);
			SWAP(data[j + 1], data[i + 1]);
		}
		m = nn;
		while (m >= 2 && j > m) {
			j -= m;
			m >>= 1;
		}
		j += m;
	}

	mmax = 2;
	while (n > mmax) {
		istep = mmax << 1;
		theta = isign * (6.28318530717959 / mmax);
		wtemp = sin(0.5 * theta);
		wpr = -2.0 * wtemp * wtemp;
		wpi = sin(theta);
		wr = 1.0;
		wi = 0.0;
		for (m = 1; m < mmax; m += 2) {
			for (i = m; i <= n; i += istep) {
				j = i + mmax;
				tempr = wr * data[j] - wi * data[j + 1];
				tempi = wr * data[j + 1] + wi * data[j];
				data[j] = data[i] - tempr;
				data[j + 1] = data[i + 1] - tempi;
				data[i] += tempr;
				data[i + 1] += tempi;
			}
			wr = (wtemp = wr) * wpr - wi * wpi + wr;
			wi = wi * wpr + wtemp * wpi + wi;
		}
		mmax = istep;
	}
}

/*************************************************************
* Purpose: This function basically preps up the data to be
*		   sent to the fft convolve function. From the notes
*		   of Manzara the x[] and y[] need to be equal size 
*		   should padded up properly and filling it up with
*		   zero for the elemnts that has no data. So function
*		   does all of that with helper functions.
*
*			This function uses the overlap-add algorithm 
**************************************************************/
float* FFTConvolve(float x[], int N, float h[], int M, float y[], int P) {
	int paddingSize = 1;
	float* DSInput;
	float* IRInput;
	float* output;
	
	while (paddingSize < P) {
		paddingSize *= 2;
	}
	
	DSInput = new float[2 *paddingSize];
	IRInput = new float[2 * paddingSize];
	output  = new float[2 * paddingSize];

	padding(DSInput, x, N, (2 * paddingSize));
	padding(IRInput, x, N, (2 * paddingSize));

	memset(output,0,(2*paddingSize)); //Zero Padding

	FFT((DSInput - 1),paddingSize,1);
	FFT((IRInput - 1), paddingSize, 1);

	complexMul(DSInput, IRInput, output, paddingSize);

	FFT((output - 1), paddingSize, -1);

	scale(output, paddingSize);

	for (int i = 0; i < P; i+=2) { 
		y[i] = output[i * 2];
		y[i + 1] = output[(i + 1) * 2];
	}

	return y;
}
/*************************************************************
* Purpsoe: This function does the complex multiplation in 
*		   Manzara's notes
**************************************************************/
void complexMul(float DSInput[], float IRInput[], float output[], int size) {
	for (int  i = 0; i < (size * 2); i+=2) { //complex multiplication
		output[i] = (DSInput[i] * IRInput[i]) - (DSInput[i + 1] * IRInput[i + 1]);
		output[i + 1 ] = (DSInput[i + 1] * IRInput[i]) - (DSInput[i] * IRInput[i + 1]);
	}
}

/*************************************************************
* Purpose: This function simply pads up the drysound and
*		   impulse response inputs to that they are the same
*		   size  when the conolution being performed. It also
*		   pads up the two data array with zeros
**************************************************************/
void padding(float X[], float Y[], int N, int P) {
	int k, i;
	for (i = 0, k = 0; k < N; k++, i += 2) {
		X[i] = Y[k];
		X[i + 1] = 0;
	}
	memset((X + k), 0, (P - 1));  // Zero padding
}
/*************************************************************
* Purpose: This function simply scales the output of the FFT
*		   as mentioned in Manzara's notes
**************************************************************/
void scale(float X[], int N) {
	int k,i;
	for (i = 0, k = 0; k < N; k++,i+=2) {
		X[i] /= (float)N;
		X[i + 1] /= (float)N;
	}
}
