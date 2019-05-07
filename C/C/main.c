#include <main.h>

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

int volt;
int num;
int dizaine;
int unite;
int temp;
int tempMax;

void afficheur1 (num) {

  dizaine = num /10 % 10;
  unite = num % 10;

  output_d(dizaine);
  output_high(act_deco_1);
  output_low(act_deco_2 );

  delay_ms(300);

  output_d(unite << 4);
  output_low(act_deco_1);
  output_high(act_deco_2);

  delay_ms(300);
}

void temperatureMax(temp) {

   tempMax = 28; // ici c'est pas 28, mais la donnée envoyée par l'app java normalement
   //quand TRISB = 0 signal sortant
   //quand TRISB = 1 signal entrant
   TRISB = 0;

   if(temp <= tempMax) {
      output_high(Led_2);
      output_low(Led_1);
   }
   else  {
      output_high(Led_1);
      delay_ms(500);
      output_low(Led_2);
   }
}

void main() {

    setup_adc_ports(AN0);
    setup_adc(ADC_CLOCK_INTERNAL);

    enable_interrupts(INT_AD);
    enable_interrupts(GLOBAL);
   
    setup_low_volt_detect(FALSE);
    set_adc_channel(0);

    enable_interrupts(INT_RDA);
    enable_interrupts(GLOBAL);


   while(TRUE) {

     volt = ((float)read_adc()  *5 /1023 *100);   //trigger an ADC conversion 

      afficheur1(volt);
      temperatureMax(volt);

   }

}