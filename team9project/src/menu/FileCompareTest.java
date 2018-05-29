package menu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileCompareTest {

    private FileCompare fc; 
    private String[] left;
    private String[] right;
    
    @Before
    public void setUp() throws Exception {
        fc = new FileCompare();
                
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void comparetest() {
        left = new String[]{"1","2","3","4","5"};
        right = new String[]{"1","2","3","4","5"};
                
        assertArrayEquals(fc.makeLCSTable(left, right), new int[][]{{0,0,0,0,0},
                                                                    {0,1,1,1,1},
                                                                    {0,1,2,2,2},
                                                                    {0,1,2,3,3},
                                                                    {0,1,2,3,4}});
        
        left = new String[]{"A", "AA", "AAA", "AAAA", "AAA", "AA", "A"};
        right = new String[]{"AA", "AA", "AA", "AA", "AA", "AA", "AA"};
        
        
        assertArrayEquals(fc.makeLCSTable(left, right), new int[][]{{0,0,0,0,0,0,0},
                                                                    {0,1,1,1,1,1,1},
                                                                    {0,1,1,1,1,2,2},
                                                                    {0,1,1,1,1,2,2},
                                                                    {0,1,1,1,1,2,2},
                                                                    {0,1,1,1,1,2,2},
                                                                    {0,1,1,1,1,2,2}});        
        
        left = new String[]{"XXX", "X X", " X ", "XXX"};
        right = new String[]{"XXX", " X ", "X X", "XX "};
        
        assertArrayEquals(fc.makeLCSTable(left, right), new int[][]{{0,0,0,0},
                                                                    {0,0,1,1},
                                                                    {0,1,1,1},
                                                                    {0,1,1,1}});
        } 
        
                
    }
    
  


