/*Name : Seifullah Elharaki*/
#include "safeBustersHeaders.h"
int computeResponse (int* countPtr);
int stillRunning = 1;
int Safe_Opened = 0;
int first;
int second;
int third;
void simple (int sigNum){
stillRunning = 0;
}

void advanced (int sigNum, siginfo_t* sigInfoPtr, void* data){
int returned;
if (first > 0){
returned = computeResponse(&first);
kill (sigInfoPtr -> si_pid, returned);}

else if (second > 0){
returned = computeResponse(&second);
kill (sigInfoPtr -> si_pid, returned);}

else if (third > 0) {
returned = computeResponse(&third);

	if (returned == SIG_RIGHT_DIGIT) {
	Safe_Opened = 1;
	}
kill (sigInfoPtr -> si_pid, returned);
}
}

int computeResponse (int* countPtr){
  int	toReturn;
  sleep(1);
  (*countPtr)--;
  if  (*countPtr == 0)
  {
    printf("Safe \"Click!\"\n");
    toReturn	= SIG_RIGHT_DIGIT;
  }
  else
  {
    printf("Safe \"Nope.\"\n");
    toReturn	= SIG_WRONG_DIGIT;
  }

  return(toReturn);
}

int main () {
struct sigaction act;
srand(getpid());
memset(&act,'\0',sizeof(act));
act.sa_handler = simple;
sigaction(SIG_QUIT,&act,NULL);
memset(&act,'\0',sizeof(act));
act.sa_sigaction = advanced;
act.sa_flags = SA_SIGINFO;
sigaction(SIG_TRY_NEXT_DIGIT,&act,NULL);
first = (rand() % 16) + 1;
second = (rand() % 16) + 1;
third = (rand() % 16) + 1;
printf("The Combination is %d %d %d\n", first,second,third);
while  (stillRunning)
  sleep(1);
if  (Safe_Opened == 1)
  printf("You have succeeded.. I concede\n");
else
  printf("You cant open this mighty safe.. give up!\n");

return (EXIT_SUCCESS);
}
