package say_desu.ru.anime1280.Application;

import android.content.Context;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import say_desu.ru.anime1280.Domain.AnimeInfo;
import say_desu.ru.anime1280.Infrastructure.AnimeRepository;

public class GameManager
{
    private AnimeRepository animeRepository;
    private List<Integer> titleList;
    private int[] randIds = {-1,-2,-3,-4};

    //genereates new title list
    public GameManager(Context context){
        animeRepository = new AnimeRepository(context);
        titleList = animeRepository.getTitleList();
    }

    //loads title list from arguments
    public GameManager(Context context, String titleString){
        animeRepository = new AnimeRepository(context);
        titleList = new ArrayList<Integer>();
        String[] titleArray = titleString.split(",");
        for(int i = 0; i<titleArray.length; i++){
            titleList.add(Integer.parseInt(titleArray[i]));
        }
    }

    private int getRandomNum(int max){
        Random random = new Random();
        int randomNum = random.nextInt(max);
        return randomNum;
    }

    public AnimeInfo getRandomAnimes(int count){
        int correctAnsIndex = getRandomNum(3); //button id where correct answer will be displayed
        randIds[correctAnsIndex] = titleList.get(count); //puts the correct ans id in the variant arr
        for(int i = 0; i<4; i++){ //fills the rest of the variant ids arr
            if(i==correctAnsIndex) continue;
            int rawRandom = getRandomNum(titleList.size());
            while (rawRandom == randIds[0] || rawRandom == randIds[1] || rawRandom == randIds[2] || rawRandom == randIds[3]){
                rawRandom = getRandomNum(titleList.size());
            }
            randIds[i] = rawRandom;
        }

        String[] variants = animeRepository.getVariants(randIds); //gets strings of the variants by ids
        String[] variants_ru = animeRepository.getVariants_ru(randIds);
        String color = animeRepository.getImageColor(randIds[correctAnsIndex]);
        AnimeInfo.TextColor imgTextColor;
        if(color.equals("white")){
            imgTextColor=AnimeInfo.TextColor.TEXTCOLOR_WHITE;
        }else{
            imgTextColor=AnimeInfo.TextColor.TEXTCOLOR_BLACK;
        }

        String imgPath = "file:///android_asset/" + (randIds[correctAnsIndex]+1) +".jpg";

        return new AnimeInfo(variants, variants_ru,correctAnsIndex,imgPath,imgTextColor);
    }

    public AnimeInfo getRandomAnimes(int[] randIds, int correctAnsIndex){
        this.randIds = randIds;
        String[] variants = animeRepository.getVariants(randIds); //gets strings of the variants by ids
        String[] variants_ru = animeRepository.getVariants_ru(randIds);
        String color = animeRepository.getImageColor(randIds[correctAnsIndex]);
        AnimeInfo.TextColor imgTextColor;
        if(color.equals("white")){
            imgTextColor=AnimeInfo.TextColor.TEXTCOLOR_WHITE;
        }else{
            imgTextColor=AnimeInfo.TextColor.TEXTCOLOR_BLACK;
        }
        String imgPath = "file:///android_asset/" + (randIds[correctAnsIndex]+1) +".jpg";

        return new AnimeInfo(variants, variants_ru,correctAnsIndex,imgPath,imgTextColor);
    }

    public int getTitlesCount(){
        return titleList.size();
    }

    public List<Integer> getTitleList() {
        return titleList;
    }

    public int[] getRandIds() {
        return randIds;
    }
}
