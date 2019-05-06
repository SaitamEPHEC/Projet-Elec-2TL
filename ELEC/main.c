#include <18F458.h>
#device ADC=10
#use delay(crystal=20000000)
#use rs232(baud=19200,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8)
#define BCD_UNITS(x) (x % 10) //unité
#define BCD_TENS(x) (x / 10) //dizaine

//void send(int temperature);
//void receive(int typeOFData,int min,int max);
void afficheUnite(int temperature);
void afficheDizaine(int temperature);
void afficheTemp(int temperature);
void gestLED(int temps, int max, int min);
void main()
{
   setup_adc_ports(AN0);
   setup_adc(ADC_CLOCK_INTERNAL); //mettre une clock interne
   setup_low_volt_detect(FALSE);
   int max = 30;
   int min = 1;
   int temperature = 20;
   int dizaine ;
   int unite;
   char typeOFData = "-50";
   
   set_adc_channel(0);
   while(TRUE)
   {
     temperature = read_adc() * 100 / 1024; //mettre en degré
     gestLED(temperature,max,min); //relation avec fonction en dessous
     afficheTemp(temperature);
   }
}
/*
void send(int temperature){
   putc(10);
   delay_ms(500);
   }
void receive(int typeOFData,int min,int max){
   char string[8];
   if (typeOFData == "-50"){
     
   if (gets(string) == "-60")
     {
     typeOFData = "-60";
     }
   if (gets(string) == "-70")
     {
     typeOFData = "-70";
     }
    }
   else {
    if (typeOFData == "-60"){min=gets(string); typeOFData = "-50";}
    if (typeOFData == "-70"){max=gets(string); typeOFData = "-50";}  
    }
   }
*/
void gestLED(int temp, int max, int min){
    if(temp > max || temp < min){
     output_high(PIN_B1); //mise à 1
     output_low(PIN_B0); //mise à 0
   }
   else{
     output_low(PIN_B1); //mise à 0
     output_high(PIN_B0); //mise à 1
   }
}

void afficheTemp(int temperature){
     while(TRUE){
         //send(temperature);
                 //receive(typeOFData,min,max);
         afficheUnite(temperature);
         delay_ms(10);
         afficheDizaine(temperature);
         delay_ms(10);
     }
}
void afficheUnite(int temperature){
         output_d(BCD_UNITS(temperature) | (1 << 4));
         output_low(PIN_D7);
         output_high(PIN_D6);
}
void afficheDizaine(int temperature){
         output_d(BCD_TENS(temperature) | (1 << 5));
         output_low(PIN_D6);
         output_high(PIN_D7);
}
