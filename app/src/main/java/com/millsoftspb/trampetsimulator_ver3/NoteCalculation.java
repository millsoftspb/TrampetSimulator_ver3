package com.millsoftspb.trampetsimulator_ver3;

public class NoteCalculation {
    //declaration
    private static int note;

    public static int calculate(boolean valve_1, boolean valve_2, boolean valve_3, int octave) {
        // valve = true - нажат
        //******D*******
        if (valve_1 & !valve_2 & valve_3) {
            note = TrumpetModel.soundD;//  >=тТт=-
        }
        //******F*******
        if (valve_1 & !valve_2 & !valve_3) {
            note = TrumpetModel.soundF;//  >=тТТ=-
        }
        //******B*******
        if (!valve_1 & valve_2 & !valve_3) {
            note = TrumpetModel.soundB;//  >=TтТ=-
        }
        //******E*******
        if (valve_1 & valve_2 & !valve_3) {
            note = TrumpetModel.soundE;//  >=ттТ=-
        }
        //******A*******
//        if (valve_1 & valve_2 & !valve_3) {
//            note = TrumpetModel.soundA;// >=TТт=-
//        }
        //******C*******
        if (!valve_1 & !valve_2 & !valve_3) {
            note = TrumpetModel.soundE;//  >=TТТ=-
        }
            return note;
        }
    }
