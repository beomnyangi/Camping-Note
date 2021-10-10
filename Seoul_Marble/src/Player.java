import java.util.Scanner;

public class Player {

    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";

    Scanner scan = new Scanner(System.in);
    int choice;
    String activity_choice;
    지연 지연_1000 = new 지연(1000);

    String name;
    int health;
    int stress;
    int athletic_ability;
    int knowledge;
    int salary;
    int coin;
    String real_estate;
    int current_area;
    String[] holding_real_estate = new String[26];
    final String[] current_area_name = {"출발지점", "강남구", "강동구" ,"강북구" ,"강서구" ,"관악구" ,"광진구" ,"구로구" ,"금천구" ,"노원구" ,"도봉구" ,"동대문구" ,"동작구" ,"마포구" ,"서대문구" ,"서초구" ,"성동구" ,"성북구" ,"송파구" ,"양천구", "영등포구" ,"용산구" ,"은평구" ,"종로구" ,"중구" ,"중랑구" };
    boolean detect_null = true;


    int monthly_supply;
    int charter_supply;
    int trading_supply;

    final int damage5 = 5;
    final int damage_5 = -5;
    final int damage10 = 10;
    final int damage_10 = -10;
    final int damage100 = 100;



    int coin_damage;
    final int museum_coin_damage = -10;
    final int teamnnova_health_damage = -10;
    final int teamnnova_knowledge_damage = 10;
    final int teamnova_coin_damage = -50;
    final int university_coin_damage = -50;
    final int academy_coin_damage = -30;


    void setting_player(String name,int health,int stress,int athletic_ability,int knowledge,int coin,int salary,String real_estate,int current_area){
        this.name = name;
        this.health = health;
        this.stress = stress;
        this.athletic_ability = athletic_ability;
        this.knowledge = knowledge;
        this.coin = coin;
        this.salary = salary;
        this.real_estate = real_estate;
        this.current_area = current_area;
    }
    void make_player(String newvalue){
        지연_1000.delay();
        System.out.print(newvalue+"의 캐릭터 이름을 정하세요 : ");
        setting_player(scan.next(), 50, 0, 0, 0, 1000, 1000, "없음", 0);
        System.out.println(newvalue+"의 캐릭터 이름은 '"+name+"'입니다");
        System.out.println();
        Print_Ability_Gauge();
    }
    void Print_Ability_Gauge() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(name+"님의 능력치");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.print("현재 위치 : "+current_area_name[current_area]);
        System.out.println("("+current_area+"/25)");
        System.out.println("체    력 : " + health + "/50");
        System.out.println("스트레스 : " + stress + "/10");
        System.out.println("운동 능력 : " + athletic_ability + "/10");
        System.out.println("지    식 : " + knowledge + "/10");
        System.out.println("보유 코인 : " + coin + "Coin");
        System.out.println("현재 월급 : " + salary + "Coin");
        System.out.println("보유중인 부동산");
        detect_null = true;
        for(int i = 0; i < holding_real_estate.length; i++) {
            if(holding_real_estate[i] != null){
                System.out.print(current_area_name[i]);
                System.out.print(" : ");
                System.out.println(holding_real_estate[i]);
                detect_null = false;
            }
        }
        if(detect_null == true){
            System.out.println("없음");
            detect_null = false;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        지연_1000.delay();
    }
    String activity_choice(){
        System.out.println(current_area_name[current_area]+"에서 할 활동을 타이핑 후 엔터를 누르세요");
        System.out.println("(띄어쓰기 없이 타이핑 하세요)");
        switch (current_area) {
            case 1,20:
                System.out.println("*.한강 산책 *.카페 가기 *.식당 가기 *.체육관 가기 *.박물관 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("한강산책") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("박물관가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 2:
                System.out.println("*.한강 산책 *.카페 가기 *.식당 가기 *.체육관 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("한강산책") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 4,16:
                System.out.println("*.한강 산책 *.카페 가기 *.식당 가기 *.체육관 가기 *.대학교 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("한강산책") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("대학교가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 6,13,15,18,21:
                System.out.println("*.한강 산책 *.카페 가기 *.식당 가기 *.체육관 가기 *.박물관 가기 *.대학교 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("한강산책") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("박물관가기") && !activity_choice.equals("대학교가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 12:
                System.out.println("*.한강 산책 *.카페 가기 *.식당 가기 *.체육관 가기 *.박물관 가기 *.팀노바 가기 *.대학교 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("한강산책") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("박물관가기") && !activity_choice.equals("팀노바가기") && !activity_choice.equals("대학교가기")  && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 3,7,8,25:
                System.out.println("*.카페 가기 *.식당 가기 *.체육관 가기 *.대학교 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("대학교가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 5,9,10,11,14,17,23,24:
                System.out.println("*.카페 가기 *.식당 가기 *.체육관 가기 *.박물관 가기 *.대학교 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("박물관가기") && !activity_choice.equals("대학교가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
            case 19,22:
                System.out.println("*.카페 가기 *.식당 가기 *.체육관 가기 *.학원 가기 *.아무것도 안 한다 *.도움말");
                activity_choice = scan.next();
                while (!activity_choice.equals("도움말") && !activity_choice.equals("카페가기") && !activity_choice.equals("식당가기") && !activity_choice.equals("체육관가기") && !activity_choice.equals("학원가기") && !activity_choice.equals("아무것도안한다")) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 할 활동을 다시 선택 하세요");
                    activity_choice = scan.next();
                }
                break;
        }
        return activity_choice;
    }
    void activity_calculation(String newvalue){
        //돈빠져나가는소리 //돈빠져나가는소리 = new //돈빠져나가는소리();
        switch (current_area) {
            case 2,3,4,5,7,8,9,10,11,12,14,17,22,23,25:
                coin_damage = -20;
                break;
            case 6,13,16,18,19,20,21,24:
                coin_damage = -30;
                break;
            case 15:
                coin_damage = -40;
                break;
            case 1:
                coin_damage = -50;
                break;
        }
        switch (newvalue) {
            case "도움말":
                System.out.println("한강 산책 : 체력+5, 스트레스-5\n" +
                                   "카페 가기 : 체력+5, 스트레스-5, 코인("+current_area_name[current_area]+" : "+coin_damage+"Coin)\n" +
                                   "식당 가기 : 체력+10, 코인("+current_area_name[current_area]+" : "+coin_damage+"Coin)\n" +
                                   "체육관 가기: 체력+5, 스트레스-5, 운동 능력+5, 코인("+current_area_name[current_area]+" : "+coin_damage+"Coin)\n" +
                                   "박물관 가기: 체력-5, 지식+5, 코인-10\n" +
                                   "팀노바 가기: 체력-10, 지식+10, 코인-50\n" +
                                   "대학교 가기: 체력-5, 지식+5, 코인-50\n" +
                                   "학원 가기 : 체력-5, 지식+5, 코인-30\n" +
                                   "아무것도 안 하기:체력-5, 스트레스+5, 운동 능력-5\n");
                break;
            case "한강산책":
                health += damage5;
                stress += damage_5;
                System.out.println("====================================한강 산책으로 인해 능력치가 증감 되었습니다========================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : +"+damage5+", 스트레스 : "+damage_5);
                System.out.println("================================================================================================================");
                지연_1000.delay();
                break;
            case "카페가기":
                if(coin < coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage5;
                stress += damage_5;
                coin += coin_damage;

                System.out.println("=============================카페에서 맛있는 음료와 디저트를 먹어서 능력치가 증감 되었습니다==============================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : +"+damage5+", 스트레스 : "+damage_5+", 보유 코인 : "+coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "식당가기":
                if(coin < coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage10;
                coin += coin_damage;
                System.out.println("=================================식당에서 맛있는 음식을 먹어서 능력치가 증감 되었습니다=================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : +"+damage10+", 보유 코인 : "+coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "체육관가기":
                if(coin < coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage5;
                stress += damage_5;
                athletic_ability += damage5;
                coin += coin_damage;
                System.out.println("====================================체육관에서 운동을 해서 능력치가 증감 되었습니다=====================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : +"+damage5+", 스트레스 : "+damage_5+", 운동능력 +: "+damage5+", 보유 코인 : "+coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "박물관가기":
                if(coin < museum_coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage_5;
                knowledge += damage5;
                coin += museum_coin_damage;
                System.out.println("====================================박물관 관람으로 인해 능력치 증감 되었습니다========================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : "+damage_5+", 지식 : +"+damage5+", 보유 코인 : "+museum_coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "팀노바가기":
                if(coin < teamnova_coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += teamnnova_health_damage;
                knowledge += teamnnova_knowledge_damage;
                coin += teamnova_coin_damage;
                System.out.println("====================================팀노바에서 공부를 해서 능력치가 증감 되었습니다=====================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : "+teamnnova_health_damage+", 지식 : +"+teamnnova_knowledge_damage+", 보유 코인 : "+teamnova_coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "대학교가기":
                if(coin < university_coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage_5;
                knowledge += damage5;
                coin += university_coin_damage;
                System.out.println("====================================대학교에서 공부를 해서 능력치가 증감 되었습니다=====================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : "+damage_5+", 지식 : +"+damage5+", 보유 코인 : "+university_coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;
            case "학원가기":
                if(coin < academy_coin_damage){
                    System.out.println("보유 코인이 모자랍니다");
                    activity_choice();
                    break;
                }
                health += damage_5;
                knowledge += damage5;
                coin += academy_coin_damage;
                System.out.println("=====================================학원에서 공부를 해서 능력치가 증감 되었습니다======================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : "+damage_5+", 지식 : +"+damage5+", 보유 코인 : "+academy_coin_damage+"Coin");
                System.out.println("================================================================================================================");
                //돈빠져나가는소리.start();
                지연_1000.delay();
                break;

            case "아무것도안한다":
                health += damage_5;
                stress += damage5;
                athletic_ability += damage_5;
                knowledge += damage_5;
                System.out.println("====================================아무런 활동을 하지 않아 능력치가 증감 되었습니다====================================");
                System.out.println("================================================================================================================");
                System.out.println("체력 : "+damage_5+", 스트레스 : +"+damage5+", 운동능력 : "+damage_5+", 지식 : "+damage_5);
                System.out.println("================================================================================================================");
                지연_1000.delay();
                break;
        }
        if(stress > 10){
            health += damage_10;
            stress = stress-10;
            System.out.println("================================================================================================================");
            System.out.println("스트레스를 많이 받아서 체력이 -10 떨어졌습니다");
            System.out.println("================================================================================================================");
            지연_1000.delay();
        }
        if(stress < 0){
            stress = 0;
        }
        if(athletic_ability > 10){
            health += damage10;
            athletic_ability = 0;
            System.out.println("================================================================================================================");
            System.out.println("운동 능력이 좋아져서 체력이 +10 올라갔습니다");
            System.out.println("================================================================================================================");
            지연_1000.delay();
        }
        if(athletic_ability < 0){
            athletic_ability = 0;
        }
        if(knowledge > 10){
            salary += damage100;
            knowledge = 0;
            System.out.println("================================================================================================================");
            System.out.println("지식이 쌓여서 월급이 +100Coin 올랐습니다");
            System.out.println("================================================================================================================");
            지연_1000.delay();
        }
        if(knowledge < 0){
            knowledge = 0;
        }

        if(health > 50){
            health = 50;
        }
        if(health < 0){
            System.out.println("체력이 모두 소진되어서 "+name+"님이 사망 했습니다");
            Game_Play.die = true;
            Game_Play.die_count++;
        }

    }
    void real_estate_contract(int newvalue){

        System.out.println(current_area_name[current_area]+"에서 부동산 계약을 하시겠습니까?");
        System.out.println("시세");

        switch (newvalue) {
            case 2,3,4,5,7,8,9,10,11,12,14,17,22,23,25:
                charter_supply = 1000;
                trading_supply = 2000;
                System.out.print("월세 : 20Coin,");
                System.out.print(" 전세 : "+charter_supply+"Coin,");
                System.out.println(" 매매 : "+trading_supply+"Coin");
                break;
            case 6,13,16,18,19,20,21,24:
                charter_supply = 1500;
                trading_supply = 3000;
                System.out.print("월세 : 30Coin,");
                System.out.print(" 전세 : "+charter_supply+"Coin,");
                System.out.println(" 매매 : "+trading_supply+"Coin");
                break;
            case 15:
                charter_supply = 2000;
                trading_supply = 4000;
                System.out.print("월세 : 40Coin,");
                System.out.print(" 전세 : "+charter_supply+"Coin,");
                System.out.println(" 매매 : "+trading_supply+"Coin");
                break;
            case 1:
                charter_supply = 2500;
                trading_supply = 5000;
                System.out.print("월세 : 50Coin,");
                System.out.print(" 전세 : "+charter_supply+"Coin,");
                System.out.println(" 매매 : "+trading_supply+"Coin");
                break;
        }
        System.out.println("1.계약 한다 2.계약 안 한다 3.계약 해지 한다");
        choice = scan.nextInt();

        while (choice < 1 || choice > 3) {
            System.out.println("잘못된 값입니다");
            System.out.println(current_area_name[current_area]+"에서 부동산 계약을 하시겠습니까?");
            choice = scan.nextInt();
        }
        if(choice == 1){
            System.out.println("어떤 형태의 부동산 계약을 하시겠습니까?");
            System.out.println("1.월세 2.전세 3.매매");
            choice = scan.nextInt();
            while (choice < 1 || choice > 3) {
                System.out.println("잘못된 값입니다");
                System.out.println("어떤 형태의 부동산 계약을 하시겠습니까?");
                choice = scan.nextInt();
            }
            switch (choice) {
                case 0:
                    System.out.println("부동산 계약을 하지 않습니다.");
                    break;
                case 1:
                    real_estate = "월세";
                    holding_real_estate[current_area] = real_estate;
                    break;
                case 2:
                    if(coin < charter_supply){
                        System.out.println("보유 코인이 모자랍니다");
                        break;
                    }
                    real_estate = "전세";
                    holding_real_estate[current_area] = real_estate;
                    coin += -charter_supply;
                    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    System.out.println("전세로 인한 지출 : -"+charter_supply+"Coin");
                    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    break;
                case 3:
                    if(coin < trading_supply){
                        System.out.println("보유 코인이 모자랍니다");
                        break;
                    }
                    real_estate = "매매";
                    holding_real_estate[current_area] = real_estate;
                    coin += -trading_supply;
                    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    System.out.println("매매로 인한 지출 : -"+trading_supply+"Coin");
                    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                    break;
            }
        }
        else if(choice == 2){
            return;
        }
        else if(choice == 3){
            if(holding_real_estate[newvalue] != null){
                System.out.print("보유중인 부동산 : ");
                System.out.println(holding_real_estate[newvalue]);
                System.out.println(current_area_name[current_area]+"에서 부동산 계약을 해지 하시겠습니까?");
                System.out.println("1.예 2.아니요");
                choice = scan.nextInt();
                while (choice < 1 || choice > 2) {
                    System.out.println("잘못된 값입니다");
                    System.out.println(current_area_name[current_area]+"에서 부동산 계약을 해지 하시겠습니까?");
                    choice = scan.nextInt();
                }
                if(choice == 1){
                    holding_real_estate[current_area] = null;
                    System.out.println(current_area_name[current_area]+"에서 부동산 계약을 해지 했습니다");
                    System.out.print("현재 보유중인 전체 부동산 : ");
                    detect_null = true;
                    for(int i = 0; i < holding_real_estate.length; i++) {
                        if(holding_real_estate[i] != null){
                            System.out.print(current_area_name[i]);
                            System.out.print(" : ");
                            System.out.println(holding_real_estate[i]);
                            detect_null = false;
                        }
                    }
                    if(detect_null == true){
                        System.out.println("없음");
                        detect_null = false;
                    }
                }
                else if(choice == 2){
                    return;
                }

            }
            else if(holding_real_estate[newvalue] == null){
                System.out.println("보유중인 부동산이 없습니다");
                return;
            }

        }
    }
    void monthly_supply(String newvalue){
        detect_null = true;
        monthly_supply = 0;
        for(int i = 0; i < holding_real_estate.length; i++) {
            if(holding_real_estate[i] != null){
                switch (i) {
                    case 2,3,4,5,7,8,9,10,11,12,14,17,22,23,25:
                        monthly_supply += 20;
                        break;
                    case 6,13,16,18,19,20,21,24:
                        monthly_supply += 30;
                        break;
                    case 15:
                        monthly_supply += 40;
                        break;
                    case 1:
                        monthly_supply += 50;
                        break;
                }
                detect_null = false;
            }
        }
        if(detect_null == true){
            monthly_supply += 20;
            detect_null = false;
        }

        switch (newvalue) {
            case "없음":
                coin += -monthly_supply;
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("부동산 미보유로 인한 지출 : -"+monthly_supply+"Coin");
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                break;
            case "월세":
                coin += -monthly_supply;
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("월세로 인한 지출 : -"+monthly_supply+"Coin");
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                break;
            case "전세":
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("전세로 인한 지출, 수입 없음");
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                break;
            case "매매":
                coin += monthly_supply;
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("부동산 보유로 인한 수입 : +"+monthly_supply+"Coin");
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                break;
        }
    }
    void salary_calculation(){
        coin += salary;
    }
    void map_print(){
        System.out.println();
        System.out.println("현위치");
        for(int i=0; i<current_area_name.length; i++) {
            if(current_area == i){
                System.out.print(ANSI_RED + current_area_name[i] + ANSI_RESET);
            }
            if(current_area != i){
                System.out.print(current_area_name[i]);
            }
            if(i < current_area_name.length-1){
                if(i == 12){
                    System.out.print("->┐");
                    System.out.println();
                    System.out.print("┌<----------------------<----------------------<----------------------<----------------------<┘");

                    System.out.println();
                    System.out.print("└>");
                }
                if(i != 12) {
                    System.out.print("->");
                }
            }
        }
        System.out.println();
        System.out.println();
        지연_1000.delay();
    }
}
