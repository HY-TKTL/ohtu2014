package ohtu;

public class Submission {
    public String student_number;
    public boolean tasks[];
    public String comments;
    public int id;
    public boolean a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21;
    public int course_id;
    public String created_at;
    public String email;
    public String first_name;
    public String github;
    public int hours;
    public String identifier;
    public String last_name;
    public int points;
    public int studen_it;
    public String updated_at;
    public int week;
    
    
    public String getStudent_number(){
        return student_number;
    }
    
    private boolean[] getTasks(){
        boolean tehdyt[] = new boolean[21];
        for(boolean tehty : tehdyt){
            tehty = false;
        }
        
        if(a1) tehdyt[0] = true;
        if(a2) tehdyt[1] = true;
        if(a3) tehdyt[2] = true;
        if(a4) tehdyt[3] = true;
        if(a5) tehdyt[4] = true;
        if(a6) tehdyt[5] = true;
        if(a7) tehdyt[6] = true;
        if(a8) tehdyt[7] = true;
        if(a9) tehdyt[8] = true;
        if(a10) tehdyt[9] = true;
        if(a11) tehdyt[10] = true;
        if(a12) tehdyt[11] = true;
        if(a13) tehdyt[12] = true;
        if(a14) tehdyt[13] = true;
        if(a15) tehdyt[14] = true;
        if(a16) tehdyt[15] = true;
        if(a17) tehdyt[16] = true;
        if(a18) tehdyt[17] = true;
        if(a19) tehdyt[18] = true;
        if(a20) tehdyt[19] = true;
        if(a21) tehdyt[20] = true;
        
        return tehdyt;
    }
    
    public int getDone(){
        
        boolean tehdyt[] = getTasks();
        
        int tehtyN = 0;
        for(boolean tehty : tehdyt){
            if(tehty) tehtyN++;
        }
        return tehtyN;
    }
    
    public int getHours(){
        return hours;
    }


    @Override
    public String toString() {
        
        int tehtyN = getDone();
        boolean tehdyt[] = getTasks();
        
        String r = "viikko "+week+": tehtyjä tehtäviä yhteensä: "+tehtyN+", aikaa kului "+hours+" tunti(a), tehdyt tehtävät: ";
        
        for(int i = 0; i < 21; i++){
            if(tehdyt[i]) r += (i+1)+" ";
        }
        return r;
    }
    
}