//
//  homework2.c
//  homework2
//
//  Created by Johnathon Ludeman on 2/16/16.
//
//

#include <stdio.h>

struct process {
	float arrival;	// 0 - 99
	float runtime;	// 0.1 - 10
	int priority;	// 1, 2, 3, 4
	char *name;	// A, B, C...
};

// just my initial thoughts on an output struct, this is not final, needs improvement
struct output {
	char *timechart;
	float avgturnaround;
	float avgwaiting;
	float avgresponse;
};

struct process processes[100];

int main() {
	// generate processes (processes should only be generated once!)
	
	
	// 1. run First-come first served
	
	
	// 2. run Shortest job first
	
	
	// 3. run Shortest remaining time
	
	
	// 4. run Round robin
	
	
	// 5. run Highest priority first (preemetive)
	
	
	// 6. run Highest priority first (non-preemptive)
	
	
	return 0;
}