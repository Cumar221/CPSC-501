// CPSC - Assignment4.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <windows.h>
#include <mmsystem.h>
#include <iostream>
#include <string>
using namespace std;


int test1()
{

	string str;
	float sound[100];
	
	

	PlaySound(TEXT("DrySounds/Sitar.wav"), NULL, SND_LOOP | SND_ASYNC);

	cout << "hey" << endl;
	cin >> str;
    return 0;
}

