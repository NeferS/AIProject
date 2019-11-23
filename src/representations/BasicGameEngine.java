package representations;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BasicGameEngine implements GameEngine {
	
	
	private final BitSet[][] captureMovesBB = {
			{
				BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x05, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x60, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x05, (byte) 0x07, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x90, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x0a, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x0a, (byte) 0x0e, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x05, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x0a, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x50, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
				BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x03, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x50, (byte) 0x70, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0xa0, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x06, (byte) 0x06, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0xa0, (byte) 0xe0, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x10, (byte) 0x00, (byte) 0x50, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x0c, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0xa0, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
				BitSet.valueOf(new byte[] { (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x03, (byte) 0x02, (byte) 0x03, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x05, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x60, (byte) 0x60, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x07, (byte) 0x05, (byte) 0x07, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x0a, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x0e, (byte) 0x0a, (byte) 0x0e, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x05, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x0c, (byte) 0x04, (byte) 0x0c, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x0a, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x20, (byte) 0x30, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x50, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x80, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x03, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x70, (byte) 0x50, (byte) 0x70, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x80, (byte) 0x00, (byte) 0xa0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x06, (byte) 0x06, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0xe0, (byte) 0xa0, (byte) 0xe0, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x50, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x0c, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0x40, (byte) 0xc0, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0xa0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x10, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x30, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x02, (byte) 0x03, }),
				BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
				BitSet.valueOf(new byte[] { (byte) 0x05, (byte) 0x00, (byte) 0x04, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x60, (byte) 0x60, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x07, (byte) 0x05, (byte) 0x07, }),
				BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
				BitSet.valueOf(new byte[] { (byte) 0x0a, (byte) 0x00, (byte) 0x08, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0xc0, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0e, (byte) 0x0a, (byte) 0x0e, }),
				BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
				BitSet.valueOf(new byte[] { (byte) 0x05, (byte) 0x00, (byte) 0x01, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x80, (byte) 0x80, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x04, (byte) 0x0c, }),
				BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
				BitSet.valueOf(new byte[] { (byte) 0x0a, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x20, (byte) 0x30, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x50, (byte) 0x00, (byte) 0x40, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x03, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x70, (byte) 0x50, (byte) 0x70, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0xa0, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x06, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xe0, (byte) 0xa0, (byte) 0xe0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x50, (byte) 0x00, (byte) 0x10, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x0c, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0x40, (byte) 0xc0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0xa0, (byte) 0x00, (byte) 0x20, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x30, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x02, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x04, }),
				BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x08, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x60, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x05, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x90, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x08, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xc0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0e, (byte) 0x0a, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x01, }),
				BitSet.valueOf(new byte[] { (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x80, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x04, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x02, }),
				BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x20, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x50, (byte) 0x00, (byte) 0x40, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x80, }),
				BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x70, (byte) 0x50, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xa0, (byte) 0x00, (byte) 0x80, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0xa0, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x50, (byte) 0x00, (byte) 0x10, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
			{
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0c, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x40, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xa0, (byte) 0x00, (byte) 0x20, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x10, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
			},
		};

	

	private final BitSet[][][] nonCaptureMovesBB = {
			{
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x0c, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x60, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x0e, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x0c, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x70, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0xe0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x60, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x0a, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0e, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x80, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x0a, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x70, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0xa0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xe0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0xa0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x90, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0e, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0a, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x50, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x70, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xa0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x50, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0c, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xa0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
			},
			{
				{
					BitSet.valueOf(new byte[] { (byte) 0x30, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x60, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x90, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0a, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0xc0, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0e, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x20, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x40, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0a, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x50, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x70, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xa0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xe0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x50, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x0c, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xa0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x30, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x60, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0a, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0xc0, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0e, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x80, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0a, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x50, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x70, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xa0, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x50, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xa0, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x30, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x07, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x90, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0e, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0c, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x30, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x70, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0c, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xc0, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x30, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x60, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xc0, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				{
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
					BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, }),
				},
				}
			};
	
	final String[][] exitMovesDirections = {
			{
			 "N", "N", "N", "N", "NW", "N", "N", "N", 
			 "NW", "N", "N", "NE", "NW", "NW", "N", "NE", 
			 "NW", "NW", "NE", "NE", "NW", "NW", "NE", "NE", 
			 "NW", "NW", "NE", "NE", "NW", "NW", "NE", "NE", 
			},
			{
			 "SW", "SW", "SE", "SE", "SW", "SW", "SE", "SE", 
			 "SW", "SW", "SE", "SE", "SW", "SW", "SE", "SE", 
			 "SW", "S", "SE", "SE", "SW", "S", "S", "SE", 
			 "S", "S", "S", "SE", "S", "S", "S", "S", 
			}
	};
	
	final int[][] exitMovesDistances = {
			{
				1, 1, 1, 1, 1, 2, 2, 2, 
				2, 3, 3, 1, 1, 3, 4, 2, 
				2, 4, 3, 1, 1, 3, 4, 2, 
				2, 4, 3, 1, 1, 3, 4, 2, 
			},
			{
				2, 4, 3, 1, 1, 3, 4, 2, 
				2, 4, 3, 1, 1, 3, 4, 2, 
				2, 4, 3, 1, 1, 3, 3, 2, 
				2, 2, 2, 1, 1, 1, 1, 1, 
			},
	};

			
	final String[] encodedSquares = {
			 "A2", "A4", "A6", "A8", "B1", "B3", "B5", "B7", 
			 "C2", "C4", "C6", "C8", "D1", "D3", "D5", "D7", 
			 "E2", "E4", "E6", "E8", "F1", "F3", "F5", "F7", 
			 "G2", "G4", "G6", "G8", "H1", "H3", "H5", "H7", 
	};
	
	private final String[][] directionMatrix = {
			{ "", "E", "E", "E", "SW", "SE", "", "", "S", "SE", "", "", "", "", "SE", "", "S", "", "SE", "", "", "", "", "SE", "S", "", "", "SE", "", "", "", "", 										},
			{ "W", "", "E", "E", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", "SE", "", "S", "", "SE", "", "", "", "", "", "S", "", "", "", "", "", "", 										},
			{ "W", "W", "", "E", "", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", "SW", "", "S", "", "SW", "", "", "", "", "", "S", "", "", "", "", "", 										},
			{ "W", "W", "W", "", "", "", "", "SW", "", "", "SW", "S", "", "", "SW", "", "", "SW", "", "S", "", "SW", "", "", "SW", "", "", "S", "SW", "", "", "", 										},
			{ "NE", "", "", "", "", "E", "E", "E", "SE", "", "", "", "S", "SE", "", "", "", "SE", "", "", "S", "", "SE", "", "", "", "SE", "", "S", "", "", "SE", 										},
			{ "NW", "NE", "", "", "W", "", "E", "E", "SW", "SE", "", "", "SW", "S", "SE", "", "", "", "SE", "", "", "S", "", "SE", "", "", "", "SE", "", "S", "", "", 										},
			{ "", "NW", "NE", "", "W", "W", "", "E", "", "SW", "SE", "", "", "SW", "S", "SE", "SW", "", "", "SE", "SW", "", "S", "", "", "", "", "", "", "", "S", "", 										},
			{ "", "", "NW", "NE", "W", "W", "W", "", "", "", "SW", "SE", "", "", "SW", "S", "", "SW", "", "", "", "SW", "", "S", "SW", "", "", "", "SW", "", "", "S", 										},
			{ "N", "NE", "", "", "NW", "NE", "", "", "", "E", "E", "E", "SW", "SE", "", "", "S", "SE", "", "", "", "", "SE", "", "S", "", "SE", "", "", "", "", "SE", 										},
			{ "NW", "N", "NE", "", "", "NW", "NE", "", "W", "", "E", "E", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", "SE", "", "S", "", "SE", "", "", "", "", 										},
			{ "", "NW", "N", "NE", "", "", "NW", "NE", "W", "W", "", "E", "", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", "SW", "", "S", "", "SW", "", "", "", 										},
			{ "", "", "NW", "N", "", "", "", "NW", "W", "W", "W", "", "", "", "", "SW", "", "", "SW", "S", "", "", "SW", "", "", "SW", "", "S", "", "SW", "", "", 										},
			{ "", "NE", "", "", "N", "NE", "", "", "NE", "", "", "", "", "E", "E", "E", "SE", "", "", "", "S", "SE", "", "", "", "SE", "", "", "S", "", "SE", "", 										},
			{ "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "", "W", "", "E", "E", "SW", "SE", "", "", "SW", "S", "SE", "", "", "", "SE", "", "", "S", "", "SE", 										},
			{ "NW", "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "W", "W", "", "E", "", "SW", "SE", "", "", "SW", "S", "SE", "SW", "", "", "SE", "SW", "", "S", "", 										},
			{ "", "NW", "", "", "", "", "NW", "N", "", "", "NW", "NE", "W", "W", "W", "", "", "", "SW", "SE", "", "", "SW", "S", "", "SW", "", "", "", "SW", "", "S", 										},
			{ "N", "", "NE", "", "", "", "NE", "", "N", "NE", "", "", "NW", "NE", "", "", "", "E", "E", "E", "SW", "SE", "", "", "S", "SE", "", "", "", "", "SE", "", 										},
			{ "", "N", "", "NE", "NW", "", "", "NE", "NW", "N", "NE", "", "", "NW", "NE", "", "W", "", "E", "E", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", "SE", 										},
			{ "NW", "", "N", "", "", "NW", "", "", "", "NW", "N", "NE", "", "", "NW", "NE", "W", "W", "", "E", "", "", "SW", "SE", "", "SW", "S", "SE", "", "SW", "", "", 										},
			{ "", "NW", "", "N", "", "", "NW", "", "", "", "NW", "N", "", "", "", "NW", "W", "W", "W", "", "", "", "", "SW", "", "", "SW", "S", "", "", "SW", "", 										},
			{ "", "", "NE", "", "N", "", "NE", "", "", "NE", "", "", "N", "NE", "", "", "NE", "", "", "", "", "E", "E", "E", "SE", "", "", "", "S", "SE", "", "", 										},
			{ "", "", "", "NE", "", "N", "", "NE", "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "", "W", "", "E", "E", "SW", "SE", "", "", "SW", "S", "SE", "", 										},
			{ "", "", "", "", "NW", "", "N", "", "NW", "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "W", "W", "", "E", "", "SW", "SE", "", "", "SW", "S", "SE", 										},
			{ "NW", "", "", "", "", "NW", "", "N", "", "NW", "", "", "", "", "NW", "N", "", "", "NW", "NE", "W", "W", "W", "", "", "", "SW", "SE", "", "", "SW", "S", 										},
			{ "N", "", "", "NE", "", "", "", "NE", "N", "", "NE", "", "", "", "NE", "", "N", "NE", "", "", "NW", "NE", "", "", "", "E", "E", "E", "SW", "SE", "", "", 										},
			{ "", "N", "", "", "", "", "", "", "", "N", "", "NE", "NW", "", "", "NE", "NW", "N", "NE", "", "", "NW", "NE", "", "W", "", "E", "E", "", "SW", "SE", "", 										},
			{ "", "", "N", "", "NW", "", "", "", "NW", "", "N", "", "", "NW", "", "", "", "NW", "N", "NE", "", "", "NW", "NE", "W", "W", "", "E", "", "", "SW", "SE", 										},
			{ "NW", "", "", "N", "", "NW", "", "", "", "NW", "", "N", "", "", "NW", "", "", "", "NW", "N", "", "", "", "NW", "W", "W", "W", "", "", "", "", "SW", 										},
			{ "", "", "", "NE", "N", "", "", "NE", "", "", "NE", "", "N", "", "NE", "", "", "NE", "", "", "N", "NE", "", "", "NE", "", "", "", "", "E", "E", "E", 										},
			{ "", "", "", "", "", "N", "", "", "", "", "", "NE", "", "N", "", "NE", "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "", "W", "", "E", "E", 										},
			{ "", "", "", "", "", "", "N", "", "", "", "", "", "NW", "", "N", "", "NW", "", "", "NE", "", "NW", "N", "NE", "", "NW", "NE", "", "W", "W", "", "E", 										},
			{ "", "", "", "", "NW", "", "", "N", "NW", "", "", "", "", "NW", "", "N", "", "NW", "", "", "", "", "NW", "N", "", "", "NW", "NE", "W", "W", "W", "", 										},
		};
	
	private final int[][] distanceMatrix = {
			{ 0, 2, 4, 6, 1, 1, -1, -1, 2, 2, -1, -1, -1, -1, 3, -1, 4, -1, 4, -1, -1, -1, -1, 5, 6, -1, -1, 6, -1, -1, -1, -1, 										},
			{ 2, 0, 2, 4, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 3, -1, 4, -1, 4, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, 										},
			{ 4, 2, 0, 2, -1, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 4, -1, 4, -1, 5, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, 										},
			{ 6, 4, 2, 0, -1, -1, -1, 1, -1, -1, 2, 2, -1, -1, 3, -1, -1, 4, -1, 4, -1, 5, -1, -1, 6, -1, -1, 6, 7, -1, -1, -1, 										},
			{ 1, -1, -1, -1, 0, 2, 4, 6, 1, -1, -1, -1, 2, 2, -1, -1, -1, 3, -1, -1, 4, -1, 4, -1, -1, -1, 5, -1, 6, -1, -1, 6, 										},
			{ 1, 1, -1, -1, 2, 0, 2, 4, 1, 1, -1, -1, 2, 2, 2, -1, -1, -1, 3, -1, -1, 4, -1, 4, -1, -1, -1, 5, -1, 6, -1, -1, 										},
			{ -1, 1, 1, -1, 4, 2, 0, 2, -1, 1, 1, -1, -1, 2, 2, 2, 3, -1, -1, 3, 4, -1, 4, -1, -1, -1, -1, -1, -1, -1, 6, -1, 										},
			{ -1, -1, 1, 1, 6, 4, 2, 0, -1, -1, 1, 1, -1, -1, 2, 2, -1, 3, -1, -1, -1, 4, -1, 4, 5, -1, -1, -1, 6, -1, -1, 6, 										},
			{ 2, 2, -1, -1, 1, 1, -1, -1, 0, 2, 4, 6, 1, 1, -1, -1, 2, 2, -1, -1, -1, -1, 3, -1, 4, -1, 4, -1, -1, -1, -1, 5, 										},
			{ 2, 2, 2, -1, -1, 1, 1, -1, 2, 0, 2, 4, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 3, -1, 4, -1, 4, -1, -1, -1, -1, 										},
			{ -1, 2, 2, 2, -1, -1, 1, 1, 4, 2, 0, 2, -1, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 4, -1, 4, -1, 5, -1, -1, -1, 										},
			{ -1, -1, 2, 2, -1, -1, -1, 1, 6, 4, 2, 0, -1, -1, -1, 1, -1, -1, 2, 2, -1, -1, 3, -1, -1, 4, -1, 4, -1, 5, -1, -1, 										},
			{ -1, 3, -1, -1, 2, 2, -1, -1, 1, -1, -1, -1, 0, 2, 4, 6, 1, -1, -1, -1, 2, 2, -1, -1, -1, 3, -1, -1, 4, -1, 4, -1, 										},
			{ -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, -1, 2, 0, 2, 4, 1, 1, -1, -1, 2, 2, 2, -1, -1, -1, 3, -1, -1, 4, -1, 4, 										},
			{ 3, -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, 4, 2, 0, 2, -1, 1, 1, -1, -1, 2, 2, 2, 3, -1, -1, 3, 4, -1, 4, -1, 										},
			{ -1, 3, -1, -1, -1, -1, 2, 2, -1, -1, 1, 1, 6, 4, 2, 0, -1, -1, 1, 1, -1, -1, 2, 2, -1, 3, -1, -1, -1, 4, -1, 4, 										},
			{ 4, -1, 4, -1, -1, -1, 3, -1, 2, 2, -1, -1, 1, 1, -1, -1, 0, 2, 4, 6, 1, 1, -1, -1, 2, 2, -1, -1, -1, -1, 3, -1, 										},
			{ -1, 4, -1, 4, 3, -1, -1, 3, 2, 2, 2, -1, -1, 1, 1, -1, 2, 0, 2, 4, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 3, 										},
			{ 4, -1, 4, -1, -1, 3, -1, -1, -1, 2, 2, 2, -1, -1, 1, 1, 4, 2, 0, 2, -1, -1, 1, 1, -1, 2, 2, 2, -1, 3, -1, -1, 										},
			{ -1, 4, -1, 4, -1, -1, 3, -1, -1, -1, 2, 2, -1, -1, -1, 1, 6, 4, 2, 0, -1, -1, -1, 1, -1, -1, 2, 2, -1, -1, 3, -1, 										},
			{ -1, -1, 5, -1, 4, -1, 4, -1, -1, 3, -1, -1, 2, 2, -1, -1, 1, -1, -1, -1, 0, 2, 4, 6, 1, -1, -1, -1, 2, 2, -1, -1, 										},
			{ -1, -1, -1, 5, -1, 4, -1, 4, -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, -1, 2, 0, 2, 4, 1, 1, -1, -1, 2, 2, 2, -1, 										},
			{ -1, -1, -1, -1, 4, -1, 4, -1, 3, -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, 4, 2, 0, 2, -1, 1, 1, -1, -1, 2, 2, 2, 										},
			{ 5, -1, -1, -1, -1, 4, -1, 4, -1, 3, -1, -1, -1, -1, 2, 2, -1, -1, 1, 1, 6, 4, 2, 0, -1, -1, 1, 1, -1, -1, 2, 2, 										},
			{ 6, -1, -1, 6, -1, -1, -1, 5, 4, -1, 4, -1, -1, -1, 3, -1, 2, 2, -1, -1, 1, 1, -1, -1, 0, 2, 4, 6, 1, 1, -1, -1, 										},
			{ -1, 6, -1, -1, -1, -1, -1, -1, -1, 4, -1, 4, 3, -1, -1, 3, 2, 2, 2, -1, -1, 1, 1, -1, 2, 0, 2, 4, -1, 1, 1, -1, 										},
			{ -1, -1, 6, -1, 5, -1, -1, -1, 4, -1, 4, -1, -1, 3, -1, -1, -1, 2, 2, 2, -1, -1, 1, 1, 4, 2, 0, 2, -1, -1, 1, 1, 										},
			{ 6, -1, -1, 6, -1, 5, -1, -1, -1, 4, -1, 4, -1, -1, 3, -1, -1, -1, 2, 2, -1, -1, -1, 1, 6, 4, 2, 0, -1, -1, -1, 1, 										},
			{ -1, -1, -1, 7, 6, -1, -1, 6, -1, -1, 5, -1, 4, -1, 4, -1, -1, 3, -1, -1, 2, 2, -1, -1, 1, -1, -1, -1, 0, 2, 4, 6, 										},
			{ -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, 5, -1, 4, -1, 4, -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, -1, 2, 0, 2, 4, 										},
			{ -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, 4, -1, 4, -1, 3, -1, -1, 3, -1, 2, 2, 2, -1, 1, 1, -1, 4, 2, 0, 2, 										},
			{ -1, -1, -1, -1, 6, -1, -1, 6, 5, -1, -1, -1, -1, 4, -1, 4, -1, 3, -1, -1, -1, -1, 2, 2, -1, -1, 1, 1, 6, 4, 2, 0, 										},
		};
	
	
	
	private Color playerColor;

	private Color enemyColor;
	
	private BitboardRepresentationNode currentBoardState;
	
	
	//Dato l'array di 12 bitboards di un giocatore, calcola una bitboard relativa a tutte le posizioni occupate
	private BitSet calculateOccupiedSquares(BitSet[] pieces) {
		
		BitSet result = new BitSet(32);
		
		for(int i = 0; i < 12; i++) {
			result.or(pieces[i]);
		}
		
		return result;
	}
	
	//Dati i due array di 12 bb ciascuno (uno per giocatore), calcola una bb relativa a tutte le caselle vuote
	private BitSet calculateEmptySquares(BitSet occupiedSquares1, BitSet occupiedSquares2) {
		
		BitSet result = new BitSet(32);
		result.flip(0, 32);
		result.andNot(occupiedSquares1);
		result.andNot(occupiedSquares2);
		
		return result;
		
	}
	
	

	
	//Calcola iterativamente tutte le coordinate delle non-capture moves e, per ciascuna di esse,
	//richiama il metodo che genera il nuovo stato della schacchera, che viene poi aggiunto alla lista
	//delle mosse valide.
	private void calculateNonCaptureMoves(BitSet[] playerPieces, 
										  BitSet[] enemyPieces,
										  BitSet playerOccupiedSquares, 
										  BitSet enemyOccupiedSquares, 
										  BitSet emptySquares,
										  List<RepresentationNode> validMoves) {
		
		//Bitboard che, per ogni coppia di iterazoni (esterna ed interna) individua,
		//alla fine dei calcoli, le posizioni su cui potrebbe spostarsi uno stack di n
		//pedine posizionato in una certa casella (srcSquare).
		BitSet reachableSquares = new BitSet(32);
		
		//Bitboard (calcolata immediatamente) che individua caselle vuote e caselle occupate
		//da pedine dello stesso colore. L'intersezione di questa bitboard con quella precedente
		//individua, ad ogni iterazione e quindi per ogni stack su una specifica casella, le posizioni
		//effettivamente raggiungibili.
		BitSet idleSquares = new BitSet(32);
		idleSquares.or(playerOccupiedSquares);
		idleSquares.or(emptySquares);
		
		//Itero su ogni tipologia di stack (stack con n + 1 pedine) 0 <= n < 12
		for(int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {
			
			//Per ogni stack con n + 1 pedine, calcolo le celle su cui potrebbe spostarsi
			//cioè celle vuote o contenenti stack dello stesso colore
			int srcSquare = 0;
			while(true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if(srcSquare == -1) break;

				//Poichè in nonCaptureMovesBB (per ciascun colore e posizione di partenza) una bitboard
				//in posizione i marca SOLO le posizioni raggiungibili a distanza i+1, se voglio valutare 
				//tutti gli spostamenti che uno stack di n+1 può fare, devo "sommare" tutte le bitboard
				//da 0 a n
				reachableSquares.clear();
				for(int i = 0; i <= playerStackSize; i++) {
					reachableSquares.or(this.nonCaptureMovesBB[this.playerColor.ordinal()][srcSquare][i]);
				}
				reachableSquares.and(idleSquares);
					
				//Estraggo le celle raggiungibili e per ognuna genero la mossa corrispondente
				int dstSquare = 0;
				while(true) {
					dstSquare = reachableSquares.nextSetBit(dstSquare);
					if(dstSquare == -1) break;
					
					String encodedSrcSquare = this.encodedSquares[srcSquare];
					String direction = directionMatrix[srcSquare][dstSquare];
					int distance = distanceMatrix[srcSquare][dstSquare];
					
					validMoves.add(BoardStateBuilder.calculateNonCaptureMove(playerPieces, 
														   enemyPieces,
														   playerOccupiedSquares,
														   playerStackSize,
														   srcSquare,
														   dstSquare,
														   encodedSrcSquare,
														   direction,
														   distance,
														   this.playerColor,
														   this.enemyColor));
					dstSquare++;
				}
				
				srcSquare++;
			}
		}
				
	}
		

		
	private void calculateExitMoves(BitSet[] playerPieces, 
									BitSet[] enemyPieces,
									BitSet playerOccupiedSquares, 
									BitSet enemyOccupiedSquares, 
									BitSet emptySquares,
									List<RepresentationNode> validMoves) {
		
		
		//Itero su ogni tipologia di stack (stack con stackSize + 1 pedine) 0 <= stackSize < 12
		for(int stackSize = 0; stackSize < 12; stackSize++) {
			
			int srcSquare = 0;
			while(true) {
				srcSquare = playerPieces[stackSize].nextSetBit(srcSquare);
				if(srcSquare == -1) break;

				int minDistance = this.exitMovesDistances[this.playerColor.ordinal()][srcSquare];
				String encodedSrcSquare = this.encodedSquares[srcSquare];
				String direction = this.exitMovesDirections[this.playerColor.ordinal()][srcSquare];
				for(int distance = minDistance; stackSize - distance >= 0; distance++) {
					BoardStateBuilder.calculateExitMove(playerPieces, 
									  enemyPieces,
									  stackSize,
									  srcSquare,
									  encodedSrcSquare,
									  direction,
									  distance,
									  this.playerColor,
									  this.enemyColor
									  );
				}
	
				srcSquare++;
			}
		}
	
	}
	



	private void calculateCapturesMoves(BitSet[] playerPieces, 
										BitSet[] enemyPieces,
								   		BitSet playerOccupiedSquares, 
								   		BitSet enemyOccupiedSquares, 
								   		BitSet emptySquares,
								   		List<RepresentationNode> validMoves) {

		

		//Bitboard che, per ogni coppia di iterazoni (esterna ed interna) individua,
		//alla fine dei calcoli, le posizioni su cui potrebbe spostarsi uno stack di n
		//pedine posizionato in una certa casella (srcSquare).
		BitSet reachableSquares = new BitSet(32);
		
		//Bitboard (calcolata immediatamente) che individua le caselle occupate dal nemico.
		//L'intersezione di questa bitboard con quella precedente individua, ad ogni iterazione 
		//i pezzi potenzialmente catturabili.
		BitSet idleSquares = new BitSet(32);
		idleSquares.or(enemyOccupiedSquares);
		
		//Itero su ogni tipologia di stack (stack con n + 1 pedine) 0 <= n < 12
		for(int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {
			
			//Per ogni stack con n + 1 pedine, calcolo le celle su cui potrebbe spostarsi
			//cioè celle vuote o contenenti stack dello stesso colore
			int srcSquare = 0;
			while(true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if(srcSquare == -1) break;

				//Poichè in captureMovesBB (per ciascun colore e posizione di partenza) una bitboard
				//in posizione i marca SOLO le posizioni raggiungibili a distanza i + 1, se voglio valutare 
				//tutti gli spostamenti che uno stack di n+1 può fare, devo "sommare" tutte le bitboard
				//da 0 a n
				reachableSquares.clear();
				for(int i = 0; i <= playerStackSize; i++) {
					reachableSquares.or(this.captureMovesBB[srcSquare][i]);
				}
				reachableSquares.and(idleSquares);
					
				//Estraggo le celle di destinazione corrispondenti a capture moves valide e, per ognuna di esse,
				//genero il nuovo stato della scacchiera.
				int dstSquare = 0;
				while(true) {
					dstSquare = reachableSquares.nextSetBit(dstSquare);
					if(dstSquare == -1) break;
					
					int enemyStackSize = -1;
					for(int i = 0; i < 12; i++) {
						if(enemyPieces[i].get(dstSquare)) {
							enemyStackSize = i;
							break;
						}
					}
					
					if(enemyStackSize == -1 || enemyStackSize > playerStackSize) break;
					
					String encodedSrcSquare = this.encodedSquares[srcSquare];
					String direction = this.directionMatrix[this.playerColor.ordinal()][srcSquare];
					int distance = this.distanceMatrix[srcSquare][dstSquare];
					
					validMoves.add(BoardStateBuilder.calculateCaptureMove(playerPieces, 
																		  enemyPieces, 
																		  playerStackSize, 
																		  enemyStackSize, 
																		  srcSquare, 
																		  dstSquare,
																		  encodedSrcSquare,
																		  direction,
																		  distance,
																		  this.playerColor,
																		  this.enemyColor
																		 ));
					dstSquare++;
				}
				
				srcSquare++;
			}
		}
		
	}
	
	
	

	@Override
	public void start(Color color) {
		this.playerColor = color;
		this.enemyColor = Color.otherColor(color);
		this.currentBoardState = new BitboardRepresentationNode();
		
		BitSet[] whitePieces = new BitSet[12];
		BitSet[] blackPieces = new BitSet[12];
		
		for(int i = 0; i < 11; i++) {
			whitePieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
			blackPieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		}
		
		whitePieces[11] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40});
		blackPieces[11] = BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		
		this.currentBoardState.setPlayerPieces(Color.WHITE, whitePieces);
		this.currentBoardState.setPlayerPieces(Color.BLACK, blackPieces);
		
	}

	@Override
	public RepresentationNode getCurrentBoardState() { return this.currentBoardState; }

	@Override
	public void playerMakeMove(RepresentationNode newBoardState) {
		
		this.currentBoardState = (BitboardRepresentationNode)newBoardState;
		
	}

	@Override
	public RepresentationNode enemyMakeMove(String encodedMove) {
		

		StringTokenizer st = new StringTokenizer(encodedMove, ",");
		
		String encodedSrcSquare = st.nextToken();
		int srcSquare = -1;
		String direction = st.nextToken();
		int distance = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i < 32; i++) {
			if(this.encodedSquares[i].equals(encodedSrcSquare)) {
				srcSquare = i;
				break;
			}
		}
		
		//System.out.println(srcSquare);
		
		int enemyStackSize = -1;
		BitSet[] enemyPieces = this.currentBoardState.getPlayerPieces(this.enemyColor);
		for(int i = 0; i < 12; i++) {
			if(enemyPieces[i].get(srcSquare)) {
				enemyStackSize = i;
				break;
			}
		}
		if(this.playerColor == Color.WHITE) { 
			//System.out.println(srcSquare);
			//System.out.println(enemyStackSize);
			//System.out.println(srcSquare);
			//System.out.println(enemyPieces[11].get(srcSquare));
		}
		
		BitSet[] playerPieces = this.currentBoardState.getPlayerPieces(this.playerColor);
		
		if(distance >= this.exitMovesDistances[this.enemyColor.ordinal()][srcSquare] && 
				   direction.equals(this.exitMovesDirections[this.enemyColor.ordinal()][srcSquare])) {
					this.currentBoardState = BoardStateBuilder.calculateExitMove(enemyPieces, 
																				 playerPieces, 
																				 enemyStackSize, 
																				 srcSquare, 
																				 encodedSrcSquare,
																				 direction,
																				 distance,
																				 enemyColor,
																				 playerColor);
					//TODO devo generare tutte le mosse di uscita possibili
		}
		
		else {
		
			int dstSquare = -1;
			LinkedList<Integer> squares = new LinkedList<Integer>();
			for(int i = 0; i < 32; i++) {
				if(this.distanceMatrix[srcSquare][i] == distance) squares.add(i);
			}
			
			while(!squares.isEmpty()) {
				int square = squares.removeFirst();
				if(direction.equals(this.directionMatrix[srcSquare][square])) {
					dstSquare = square;
					break;
				}
			}
			
			BitSet playerOccupiedSquares = this.calculateOccupiedSquares(playerPieces);
			if(!playerOccupiedSquares.get(dstSquare)) {
				BitSet enemyOccupiedSquares = this.calculateOccupiedSquares(enemyPieces);
				
				this.currentBoardState = BoardStateBuilder.calculateNonCaptureMove(enemyPieces, 
																	  			   playerPieces, 
																	  			   enemyOccupiedSquares, 
																	  			   enemyStackSize, 
																	  			   srcSquare, 
																	  			   dstSquare,
																	  			   encodedSrcSquare,
																	  			   direction,
																	  			   distance,
																	  			   this.enemyColor,
																	  			   this.playerColor);
			}
			else {
				
				int playerStackSize = -1;
				for(int i = 0; i < 12; i++) {
					if(playerPieces[i].get(dstSquare)) {
						playerStackSize = i;
						break;
					}
				}
				
				
				this.currentBoardState = BoardStateBuilder.calculateCaptureMove(enemyPieces,
																   				playerPieces,
																   				enemyStackSize,
																   				playerStackSize,
																   				srcSquare,
																   				dstSquare,
																   				encodedSrcSquare,
																   				direction,
																   				distance,
																   				this.enemyColor,
																   				this.playerColor);
																   
			}
			
		}
		
		
		return this.currentBoardState;
	}
	
	
	public List<RepresentationNode> validActions(RepresentationNode configuration) {
		 
		BitboardRepresentationNode concreteBoardState = (BitboardRepresentationNode)configuration;
		List<RepresentationNode> validMoves = new LinkedList<RepresentationNode>();
		
		BitSet[] playersOccupiedSquares = new BitSet[2];
		playersOccupiedSquares[this.playerColor.ordinal()] = calculateOccupiedSquares(concreteBoardState.getPlayerPieces(this.playerColor));
		playersOccupiedSquares[this.enemyColor.ordinal()] = calculateOccupiedSquares(concreteBoardState.getPlayerPieces(this.enemyColor));
		BitSet emptySquares = calculateEmptySquares(playersOccupiedSquares[0], playersOccupiedSquares[1]);
		
		calculateNonCaptureMoves(concreteBoardState.getPlayerPieces(this.playerColor), 
				 				 concreteBoardState.getPlayerPieces(this.enemyColor), 
				 				 playersOccupiedSquares[this.playerColor.ordinal()], 
				 				 playersOccupiedSquares[this.enemyColor.ordinal()], 
				 				 emptySquares, 
				 				 validMoves);
		/*
		calculateExitMoves(concreteBoardState.getPlayerPieces(this.playerColor),
						   concreteBoardState.getPlayerPieces(this.enemyColor),
						   playersOccupiedSquares[this.playerColor.ordinal()], 
						   playersOccupiedSquares[this.enemyColor.ordinal()], 
						   emptySquares, 
						   validMoves);
						   */
		/*
		calculateCapturesMoves(concreteBoardState.getPlayerPieces(this.playerColor), 
							   concreteBoardState.getPlayerPieces(this.enemyColor),
							   playersOccupiedSquares[this.playerColor.ordinal()], 
							   playersOccupiedSquares[this.enemyColor.ordinal()], 
							   emptySquares, 
							   validMoves);
		 */
		if(this.playerColor == Color.WHITE) {
		
		}
		
		return validMoves;
		
	}
	

}
