package com.ppwqdxlte.basic.class08;

import static com.ppwqdxlte.basic.class07.Code01_RecursiveTraversalBT.*;
/**
 * @author:李罡毛
 * @date:2021/7/26 19:44
 * 【提问】纸条垂直，不反转不翻个儿，面对自己，从下往上折起，折N次，折痕是啥样的？
 */
public class Code04_PaperFolding {
    public void paperFolding(int N){
        if (N == 0) return;
        fold(1,N,true);
    }
    private void fold(int l,int N,boolean down){
        if (l > N) return;
        fold(l + 1,N,true);
        System.out.println(down?"凹":"凸");
        fold(l + 1,N,false);
    }

    public static void main(String[] args) {
        Code04_PaperFolding test = new Code04_PaperFolding();
        int N = 3;
        test.paperFolding(N);
    }

}
