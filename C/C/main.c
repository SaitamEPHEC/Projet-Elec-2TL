
#include <18F458.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#byte TRISB = 0xF93
#byte TRISD = 0xF95
#byte TRISE = 0xF96

#define Led_1 PIN_B0 
#define Led_2 PIN_B1
#define Aff_A PIN_D0
#define Aff_B PIN_D1
#define Aff_C PIN_D2
#define Aff_D PIN_D3
#define Aff2_B PIN_D5
#define act_deco_1 PIN_E0
#define act_deco_2 PIN_E1
#use delay(clock = 20000000)
#use rs232(baud=9600,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8)
 int limite = 0;
#int_rda
void isr() {
    char seuil[10];
    gets(seuil);
     
     limite = atoi(seuil);
    //seuil =  atoi(treshstr);
   
   disable_interrupts(INT_RDA);

  
}
void afficheur1 (int num){

int dizaine = num /10 % 10;
int unite = num % 10;
output_d(dizaine );
output_high(act_deco_1);
output_low(act_deco_2 );
delay_ms(300);

output_d(unite << 4);
output_low(act_deco_1);
output_high(act_deco_2);
delay_ms(300);

}

void temperatureMax(int temp){

   int tempMax = 28;
   //quand TRISB = 0 signal sortant
   //quand TRISB = 1 signal entrant
   TRISB = 0;

   if(temp <= tempMax){
      output_high(Led_2);
      output_low(Led_1);
   }
   else{
      output_high(Led_1);
      delay_ms(500);
      output_low(Led_2);
   }
}

void main()
{
enable_interrupts(GLOBAL);
   setup_adc_ports(ALL_ANALOG);
   setup_low_volt_detect(FALSE);
   TRISB = 0;
   TRISD = 0;

   while(TRUE)
   {
   
      output_high(Led_1);
      delay_ms(500);
      output_low(Led_1);
      delay_ms(500);
      
      output_high(Led_2);
      delay_ms(500);
     
      afficheur1(25);
     
      
   }

}
