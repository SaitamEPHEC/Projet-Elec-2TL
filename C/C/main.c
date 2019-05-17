#include <CodeC.h>

#byte TRISB = 0xF93
#byte TRISD = 0xF95
#byte TRISE = 0xF96

#define Led_1 PIN_B0 
#define Led_2 PIN_B1
#define act_deco_1 PIN_E0
#define act_deco_2 PIN_E1
#use delay(clock = 20000000)
#use rs232(baud=9600,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8)

int ADC;
int num;
int dizaine;
int unite;
int temp;
int valEnvoi;
int value= 0;
int i = 0;
char buffer[3];
boolean flag = 0 ;

#int_rda
void isr() {
    while(flag == 0 ){
        buffer[i] = getc();
        disable_interrupts(INT_RDA);
        i++;
        if(i == 2){
         flag = 1;
         i = 0;
        }
    }
}
   
void septSegments (int num){
   int dizaine = num /10 % 10;
   int unite = num % 10;
   output_d(dizaine);
   output_high(act_deco_1);
   output_low(act_deco_2);
   delay_ms(300);
   output_d(unite << 4);
   output_low(act_deco_1);
   output_high(act_deco_2);
   delay_ms(300);
}

void temperatureMax(int temp,valEnvoi){
   
   //quand TRISB = 0 signal sortant
   //quand TRISB = 1 signal entrant
   TRISB = 0;
   if(temp <= valEnvoi){
      output_high(Led_2);
      output_low(Led_1);
   }
   else{
      output_high(Led_1);
      output_low(Led_2);
   }
}

void main()
{
    setup_adc_ports(AN0);
    setup_low_volt_detect(FALSE);
    set_adc_channel(0);
    setup_adc(ADC_CLOCK_INTERNAL);
    setup_low_volt_detect(FALSE);
   while(TRUE) {
      enable_interrupts(INT_RDA);
      enable_interrupts(GLOBAL);
      if (flag) {
         flag = 0;
         valEnvoi = (buffer[0] - 48)*10 + (buffer[1] -48);
         printf("%d",valEnvoi);
      }
      ADC = ((float)read_adc()  *5 /1023 *100); 
      septSegments(ADC);
      temperatureMax(ADC,valEnvoi);
      if (ADC != value) {
         value = ADC;
         printf("%d",ADC);
      }
   }
}
