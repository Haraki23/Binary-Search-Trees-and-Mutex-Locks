/*-------------------------------------------------------------------------*
 *---									---*
 *---		lookoutThief.c						---*
 *---									---*
 *---	    This file defines the lookoutThief program, that starts the ---*
 *---	safe and crackingThief programs, and oversees the whole		---*
 *---	application.							---*
 *---									---*
 *---	----	----	----	----	----	----	----	----	---*
 *---									---*
 *---	Version 1a		2018 April 20		Joseph Phillips	---*
 *---Name: Seifullah Elharaki						---*
 *-------------------------------------------------------------------------*/

#include	"safeBustersHeaders.h"

#define		TEXT_LEN	64

int		isCoastClear	= 1;

int		haveTheGoods	= 0;

pid_t		safePid;

pid_t		crackingThiefPid;

void		sigAlarmHandler	(int	sigInt
				)
{
  printf("Watching thief \"Forget the safe!  We gotta go!\"\n");
  isCoastClear	= 0;
  //  YOUR CODE HERE to send SIG_QUIT to both safePid and crackingThiefPid
  kill (safePid, SIG_QUIT);
  kill (crackingThiefPid, SIG_QUIT);
}


void		sigChildHandler	(int	sigInt
				)
{
  if  (isCoastClear)
  {
    printf("Watching thief \"Good job, let's skedaddle!\"\n");
    haveTheGoods	= 1;
  }

  //  YOUR CODE HERE to send SIG_QUIT to safePid
  kill (safePid, SIG_QUIT);
}


int		main		()
{
  int			time;
  struct sigaction	act;
  //  YOUR CODE HERE
  //  Install sigAlarmHandler() as the SIGALRM handler
  //  Install sigChildHandler() as the SIGCHLD handler
  memset(&act,'\0',sizeof(act));
  act.sa_handler = sigAlarmHandler;
  sigaction(SIGALRM,&act,NULL);
  act.sa_handler = sigChildHandler;
  sigaction(SIGCHLD,&act,NULL);

  safePid	= fork(); 
  if  (safePid == 0)
  {  
    execl("safe","safe",NULL);
    //YOUR CODE HERE to start safe
    fprintf(stderr,"Watching thief \"Where's the freaking safe?!\"\n");
    exit(EXIT_FAILURE);
  }

  sleep(1);
  crackingThiefPid = fork();
 

  if  (crackingThiefPid == 0)
  {
    char	pidText[TEXT_LEN];

    snprintf(pidText,TEXT_LEN,"%d",safePid);
    execl("crackingThief","crackingThief",pidText,NULL);
    //YOUR CODE HERE to start crackingThief with pidText as a command line argument
    fprintf(stderr,"Watching thief \"Where's my partner in crime?!\"\n");
    exit(EXIT_FAILURE);
    
  }

  //  YOUR CODE HERE to get sent SIGALRM NUM_SECS_BEFORE_MUST_GO seconds in the future
  alarm(NUM_SECS_BEFORE_MUST_GO);
  for  (time = 0;  isCoastClear && !haveTheGoods;  time++)
  {
    if  ( (time % NUM_SECS_BETWEEN_HURRY_UPS) == 0 )
      printf("Watching thief \"Hurry it up!\"\n");

    sleep(1);
  }

  //  YOUR CODE HERE to wait for both child processes
  int status;
  int status2;
  pid_t safe_wait = waitpid(safePid, &status, 0);
 // printf("Child %d ended with status %d\n", safePid,WEXITSTATUS(status));
  pid_t cracking_wait = waitpid(crackingThiefPid, &status2, 0);
 // printf("Child %d ended with status %d\n", crackingThiefPid,WEXITSTATUS(status));
  printf("(bye)\n");
  return(EXIT_SUCCESS);
}
