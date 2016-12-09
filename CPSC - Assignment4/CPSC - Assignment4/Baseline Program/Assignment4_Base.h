#ifndef ASSIGNMENT4_H
#define ASSIGNMENT4_H
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

wave_header myWave;
float* convolve(float x[], int N, float h[], int M, float y[], int P);
float* readWaveFile(char *inputFilePath, float *inputSignal);
void writeWaveFile(char* outputFilePath, float* outputSignal, int outputSignalSize);

#endif