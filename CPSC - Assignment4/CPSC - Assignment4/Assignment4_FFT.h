#ifndef ASSIGNMENT4_H
#define ASSIGNMENT4_H
#define SWAP(a,b) tempr = (a);(a)=(b);(b)=tempr
#include <stdint.h>

typedef struct  WAVE_HEADER{
	char			RIFF[4];
	int				ChunkSize;
	char			WAVE[4];
	char			FMT[4];
	int				subChunk1Size;
	uint16_t			audioFormat;
	uint16_t			numChannels;
	int				sampleRate;
	int				byteRate;
	uint16_t			blockAlign;
	uint16_t			bitsPerSample;
	char			subChunk2ID[4];
	int				subChunk2Size;
	short*			fileData;
	int				dataSize;
	float*			signal;
}wave_header;

wave_header FFTMyWave;
float* FFTReadWaveFile(char *inputFilePath, float *inputSignal);
void   FFTWriteWaveFile(char* outputFilePath, float* outputSignal, int outputSignalSize);
float* convolve(float x[], int N, float h[], int M, float y[], int P);
void   scale(float X[], int N);
float* FFTConvolve(float x[], int N, float h[], int M, float y[], int P);
void   FFT(float data[], int nn, int isign);
void padding(float X[], float Y[], int N, int P);
void complexMul(float DSInput[], float IRInput[], float output[], int size);

#endif