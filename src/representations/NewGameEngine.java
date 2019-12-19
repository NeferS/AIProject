package representations;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class NewGameEngine implements GameEngine {
	
	public static final BitSet[][] captureMovesBB = {
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

	public static final BitSet[][][] nonCaptureMovesBB = {
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

	public static final int[][][] exitMoves = {
			{
					{2, -1, -1, -1, 1, 1, -1, -1, },
					{2, -1, -1, -1, 1, 1, -1, -1, },
					{2, -1, -1, -1, 1, 1, -1, -1, },
					{2, -1, -1, -1, 1, 1, -1, -1, },
					{2, -1, -1, -1, 1, 2, -1, -1, },
					{2, -1, -1, -1, 2, 2, -1, -1, },
					{2, -1, -1, -1, 2, 2, -1, -1, },
					{2, -1, -1, -1, 2, 2, -1, -1, },
					{4, -1, -1, -1, 2, 3, -1, -1, },
					{4, -1, -1, -1, 3, 3, -1, -1, },
					{4, -1, -1, -1, 3, 3, -1, -1, },
					{4, -1, -1, -1, 3, 1, -1, -1, },
					{4, -1, -1, -1, 1, 4, -1, -1, },
					{4, -1, -1, -1, 3, 4, -1, -1, },
					{4, -1, -1, -1, 4, 4, -1, -1, },
					{4, -1, -1, -1, 4, 2, -1, -1, },
					{6, -1, -1, -1, 2, 5, -1, -1, },
					{6, -1, -1, -1, 4, 5, -1, -1, },
					{6, -1, -1, -1, 5, 3, -1, -1, },
					{6, -1, -1, -1, 5, 1, -1, -1, },
					{6, -1, -1, -1, 1, 6, -1, -1, },
					{6, -1, -1, -1, 3, 6, -1, -1, },
					{6, -1, -1, -1, 5, 4, -1, -1, },
					{6, -1, -1, -1, 6, 2, -1, -1, },
					{8, -1, -1, -1, 2, 7, -1, -1, },
					{8, -1, -1, -1, 4, 5, -1, -1, },
					{8, -1, -1, -1, 6, 3, -1, -1, },
					{8, -1, -1, -1, 7, 1, -1, -1, },
					{8, -1, -1, -1, 1, 8, -1, -1, },
					{8, -1, -1, -1, 3, 6, -1, -1, },
					{8, -1, -1, -1, 5, 4, -1, -1, },
					{8, -1, -1, -1, 7, 2, -1, -1, },
			},
			{
					{-1, 8, -1, -1, -1, -1, 2, 7, },
					{-1, 8, -1, -1, -1, -1, 4, 5, },
					{-1, 8, -1, -1, -1, -1, 6, 3, },
					{-1, 8, -1, -1, -1, -1, 8, 1, },
					{-1, 8, -1, -1, -1, -1, 1, 7, },
					{-1, 8, -1, -1, -1, -1, 3, 6, },
					{-1, 8, -1, -1, -1, -1, 5, 4, },
					{-1, 8, -1, -1, -1, -1, 7, 2, },
					{-1, 6, -1, -1, -1, -1, 2, 6, },
					{-1, 6, -1, -1, -1, -1, 4, 5, },
					{-1, 6, -1, -1, -1, -1, 6, 3, },
					{-1, 6, -1, -1, -1, -1, 6, 1, },
					{-1, 6, -1, -1, -1, -1, 1, 5, },
					{-1, 6, -1, -1, -1, -1, 3, 5, },
					{-1, 6, -1, -1, -1, -1, 5, 4, },
					{-1, 6, -1, -1, -1, -1, 5, 2, },
					{-1, 4, -1, -1, -1, -1, 2, 4, },
					{-1, 4, -1, -1, -1, -1, 4, 4, },
					{-1, 4, -1, -1, -1, -1, 4, 3, },
					{-1, 4, -1, -1, -1, -1, 4, 1, },
					{-1, 4, -1, -1, -1, -1, 1, 3, },
					{-1, 4, -1, -1, -1, -1, 3, 3, },
					{-1, 4, -1, -1, -1, -1, 3, 3, },
					{-1, 4, -1, -1, -1, -1, 3, 2, },
					{-1, 2, -1, -1, -1, -1, 2, 2, },
					{-1, 2, -1, -1, -1, -1, 2, 2, },
					{-1, 2, -1, -1, -1, -1, 2, 2, },
					{-1, 2, -1, -1, -1, -1, 2, 1, },
					{-1, 2, -1, -1, -1, -1, 1, 1, },
					{-1, 2, -1, -1, -1, -1, 1, 1, },
					{-1, 2, -1, -1, -1, -1, 1, 1, },
					{-1, 2, -1, -1, -1, -1, 1, 1, },
			},
	};
			
	public static final String[] encodedSquares = {
			 "A2", "A4", "A6", "A8", "B1", "B3", "B5", "B7", 
			 "C2", "C4", "C6", "C8", "D1", "D3", "D5", "D7", 
			 "E2", "E4", "E6", "E8", "F1", "F3", "F5", "F7", 
			 "G2", "G4", "G6", "G8", "H1", "H3", "H5", "H7", 
	};
	
	public static final String[][] directions = {
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
	
	public static final int[][] distances = {
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
	
	
	private Color playerColor, enemyColor;
	private BitboardRepresentationNode currentBoardState;
	
	@Override
	public void start(Color color) {
		playerColor = color;
		enemyColor = Color.otherColor(color);
		currentBoardState = new BitboardRepresentationNode();
		
		BitSet[] whitePieces = new BitSet[12];
		BitSet[] blackPieces = new BitSet[12];
		
		for(int i = 0; i < 11; i++) {
			whitePieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
			blackPieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		}
		
		whitePieces[11] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40});
		blackPieces[11] = BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		
		currentBoardState.setPlayerPieces(Color.WHITE, whitePieces);
		currentBoardState.setPlayerPieces(Color.BLACK, blackPieces);
		
	}
	
	@Override
	public Color getPlayerColor() { return playerColor; }
	
	@Override
	public Color getEnemyColor() { return enemyColor; }

	@Override
	public RepresentationNode getCurrentBoardState() { return currentBoardState; }

	@Override
	public void playerMakeMove(RepresentationNode newBoardState) { currentBoardState = (BitboardRepresentationNode)newBoardState; }

	@Override 
	public RepresentationNode enemyMakeMove(String encodedMove) {
		StringTokenizer st = new StringTokenizer(encodedMove, ",");
		
		String encodedSrcSquare = st.nextToken();
		String direction = st.nextToken();
		int distance = Integer.valueOf(st.nextToken());
		
		//mossa vuota
		if(distance == 0) {
			currentBoardState.setMove(encodedMove);
			return currentBoardState;
		}
		
		//altrimenti
		int col = Integer.parseInt(""+encodedSrcSquare.charAt(1));
		int srcSquare = ((char)encodedSrcSquare.charAt(0)-65)*4;
		srcSquare += (col%2 == 0)? col/2-1 : col/2;
		
		int enemyStackSize = -1;
		BitSet[] enemyPieces = currentBoardState.getPlayerPieces(enemyColor);
		for(int i = 0; i < 12; i++)
			if(enemyPieces[i].get(srcSquare)) {
				enemyStackSize = i;
				break;
			}
		
		//mossa di uscita
		for(int i = 0; i < 8; i++)
			if(exitMoves[enemyColor.ordinal()][srcSquare][i] != -1 && 
			   exitMoves[enemyColor.ordinal()][srcSquare][i] <= distance &&
			   Direction.getDirectionByInt(i).toString().equals(direction))
			{
				return currentBoardState = BoardStateBuilder.calculateExitMove(
						enemyPieces,
						currentBoardState.getPlayerPieces(playerColor),
						enemyStackSize, 
						srcSquare, 
						encodedSrcSquare, 
						direction, 
						distance, 
						enemyColor, 
						playerColor);
			}
		
		//altrimenti
		BitSet[] playerPieces = currentBoardState.getPlayerPieces(playerColor);
		int dstSquare = -1;
		for (int i = 0; i < 32; i++) {
			if (distances[srcSquare][i] == distance && direction.equals(directions[srcSquare][i])) {
				dstSquare = i;
				break;
			}
		}

		BitSet playerOccupiedSquares = BoardStateBuilder.calculateOccupiedSquares(playerPieces);
		//nonCaptureMove
		if (!playerOccupiedSquares.get(dstSquare)) {
			BitSet enemyOccupiedSquares = BoardStateBuilder.calculateOccupiedSquares(enemyPieces);

			return currentBoardState = BoardStateBuilder.calculateNonCaptureMove(enemyPieces, playerPieces,
					enemyOccupiedSquares, enemyStackSize, srcSquare, dstSquare, encodedSrcSquare, direction, distance,
					enemyColor, playerColor);
		} 
		
		//altrimenti : captureMove
		int playerStackSize = -1;
		for (int i = 0; i < distance; i++)
			if (playerPieces[i].get(dstSquare)) {
				playerStackSize = i;
				break;
			}
		return currentBoardState = BoardStateBuilder.calculateCaptureMove(enemyPieces, playerPieces, enemyStackSize,
				playerStackSize, srcSquare, dstSquare, encodedSrcSquare, direction, distance, enemyColor, playerColor);
	}
	
	@Override
	public List<RepresentationNode> validActions(RepresentationNode configuration, Color playingColor) {
		
		Color otherPlayer = Color.otherColor(playingColor);
		BitboardRepresentationNode concreteBoardState = (BitboardRepresentationNode)configuration;
		List<RepresentationNode> validMoves = new LinkedList<RepresentationNode>();
		
		BitSet[] playersOccupiedSquares = new BitSet[2];
		playersOccupiedSquares[playingColor.ordinal()] = BoardStateBuilder.calculateOccupiedSquares(concreteBoardState.getPlayerPieces(playingColor));
		playersOccupiedSquares[otherPlayer.ordinal()] = BoardStateBuilder.calculateOccupiedSquares(concreteBoardState.getPlayerPieces(otherPlayer));
		BitSet emptySquares = BoardStateBuilder.calculateEmptySquares(playersOccupiedSquares[0], playersOccupiedSquares[1]);
		
		
		calculateNonCaptureMoves(concreteBoardState.getPlayerPieces(playingColor), 
				 				 concreteBoardState.getPlayerPieces(otherPlayer), 
				 				 playersOccupiedSquares[playingColor.ordinal()], 
				 				 playersOccupiedSquares[otherPlayer.ordinal()], 
				 				 emptySquares,
				 				 playingColor,
				 				 otherPlayer,
				 				 validMoves);
		
		calculateCapturesMoves(concreteBoardState.getPlayerPieces(playingColor), 
				   concreteBoardState.getPlayerPieces(otherPlayer),
				   playersOccupiedSquares[playingColor.ordinal()], 
				   playersOccupiedSquares[otherPlayer.ordinal()], 
				   emptySquares,
				   playingColor,
				   otherPlayer,
				   validMoves);
		
		calculateExitMoves(concreteBoardState.getPlayerPieces(playingColor),
						   concreteBoardState.getPlayerPieces(otherPlayer),
						   playersOccupiedSquares[playingColor.ordinal()], 
						   playersOccupiedSquares[otherPlayer.ordinal()], 
						   emptySquares,
						   playingColor,
						   otherPlayer,
						   validMoves);
		
		if(validMoves.isEmpty()) {
			RepresentationNode node = calculateEmptyMove(concreteBoardState, playingColor);
			if(node != null)
				validMoves.add(node);
		}

		return validMoves;
	}
	
	//Calcola iterativamente tutte le coordinate delle non-capture moves e, per ciascuna di esse,
	//richiama il metodo che genera il nuovo stato della schacchera, che viene poi aggiunto alla lista
	//delle mosse valide.
	private void calculateNonCaptureMoves(BitSet[] playerPieces, BitSet[] enemyPieces, BitSet playerOccupiedSquares,
			BitSet enemyOccupiedSquares, BitSet emptySquares, Color playerColor, Color enemyColor, List<RepresentationNode> validMoves) {

		// Bitboard che, per ogni coppia di iterazoni (esterna ed interna) individua,
		// alla fine dei calcoli, le posizioni su cui potrebbe spostarsi uno stack di n
		// pedine posizionato in una certa casella (srcSquare).
		BitSet reachableSquares = new BitSet(32);

		// Bitboard (calcolata immediatamente) che individua caselle vuote e caselle
		// occupate
		// da pedine dello stesso colore. L'intersezione di questa bitboard con quella
		// precedente
		// individua, ad ogni iterazione e quindi per ogni stack su una specifica
		// casella, le posizioni
		// effettivamente raggiungibili.
		BitSet idleSquares = new BitSet(32);
		idleSquares.or(playerOccupiedSquares);
		idleSquares.or(emptySquares);

		// Itero su ogni tipologia di stack (stack con n + 1 pedine) 0 <= n < 12
		for (int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {

			// Per ogni stack con n + 1 pedine, calcolo le celle su cui potrebbe spostarsi
			// cioè celle vuote o contenenti stack dello stesso colore
			int srcSquare = 0;
			while (true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if (srcSquare == -1)
					break;

				// Poichè in nonCaptureMovesBB (per ciascun colore e posizione di partenza) una
				// bitboard
				// in posizione i marca SOLO le posizioni raggiungibili a distanza i+1, se
				// voglio valutare
				// tutti gli spostamenti che uno stack di n+1 può fare, devo "sommare" tutte le
				// bitboard
				// da 0 a n
				reachableSquares.clear();
				for (int i = 0; i <= playerStackSize; i++) {
					reachableSquares.or(nonCaptureMovesBB[playerColor.ordinal()][srcSquare][i]);
				}
				reachableSquares.and(idleSquares);

				// Estraggo le celle raggiungibili e per ognuna genero la mossa corrispondente
				int dstSquare = 0;
				while (true) {
					dstSquare = reachableSquares.nextSetBit(dstSquare);
					if (dstSquare == -1)
						break;

					String encodedSrcSquare = encodedSquares[srcSquare];
					String direction = directions[srcSquare][dstSquare];
					int distance = distances[srcSquare][dstSquare];

					validMoves.add(BoardStateBuilder.calculateNonCaptureMove(playerPieces, enemyPieces,
							playerOccupiedSquares, playerStackSize, srcSquare, dstSquare, encodedSrcSquare, direction,
							distance, playerColor, enemyColor));
					dstSquare++;
				}
				srcSquare++;
			}
		}

	}
	
	private void calculateCapturesMoves(BitSet[] playerPieces, BitSet[] enemyPieces, BitSet playerOccupiedSquares,
			BitSet enemyOccupiedSquares, BitSet emptySquares, Color playerColor, Color enemyColor, List<RepresentationNode> validMoves) {

		// Bitboard che, per ogni coppia di iterazoni (esterna ed interna) individua,
		// alla fine dei calcoli, le posizioni su cui potrebbe spostarsi uno stack di n
		// pedine posizionato in una certa casella (srcSquare).
		BitSet reachableSquares = new BitSet(32);

		// Bitboard (calcolata immediatamente) che individua le caselle occupate dal
		// nemico.
		// L'intersezione di questa bitboard con quella precedente individua, ad ogni
		// iterazione
		// i pezzi potenzialmente catturabili.
		BitSet idleSquares = new BitSet(32);
		idleSquares.or(enemyOccupiedSquares);

		// Itero su ogni tipologia di stack (stack con n + 1 pedine) 0 <= n < 12
		for (int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {

			// Per ogni stack con n + 1 pedine, calcolo le celle su cui potrebbe spostarsi
			// cioe celle vuote o contenenti stack dello stesso colore
			int srcSquare = 0;
			while (true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if (srcSquare == -1)
					break;

				// Poiche in captureMovesBB (per ciascun colore e posizione di partenza) una
				// bitboard
				// in posizione i marca SOLO le posizioni raggiungibili a distanza i + 1, se
				// voglio valutare
				// tutti gli spostamenti che uno stack di n+1 può fare, devo "sommare" tutte le
				// bitboard
				// da 0 a n
				reachableSquares.clear();
				for (int i = 0; i <= playerStackSize; i++) {
					reachableSquares.or(captureMovesBB[srcSquare][i]);
				}
				reachableSquares.and(idleSquares);

				// Estraggo le celle di destinazione corrispondenti a capture moves valide e,
				// per ognuna di esse,
				// genero il nuovo stato della scacchiera.
				int dstSquare = 0;
				while (true) {
					dstSquare = reachableSquares.nextSetBit(dstSquare);
					if (dstSquare == -1)
						break;

					int enemyStackSize = -1;
					for (int i = 0; i < 12; i++) {
						if (enemyPieces[i].get(dstSquare)) {
							enemyStackSize = i;
							break;
						}
					}
					int distance = distances[srcSquare][dstSquare];

					if (distance - 1 >= enemyStackSize && playerStackSize >= enemyStackSize) {
						String encodedSrcSquare = encodedSquares[srcSquare];
						String direction = directions[srcSquare][dstSquare];

						validMoves.add(BoardStateBuilder.calculateCaptureMove(playerPieces, enemyPieces,
								playerStackSize, enemyStackSize, srcSquare, dstSquare, encodedSrcSquare, direction,
								distance, playerColor, enemyColor));
					}
					dstSquare++;
				}
				srcSquare++;
			}
		}
	}
	
	private void calculateExitMoves(BitSet[] playerPieces, BitSet[] enemyPieces, BitSet playerOccupiedSquares,
			BitSet enemyOccupiedSquares, BitSet emptySquares, Color playerColor, Color enemyColor, List<RepresentationNode> validMoves) {

		// Itero su ogni tipologia di stack (stack con stackSize + 1 pedine) 0 <=
		// stackSize < 12
		for (int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {

			int srcSquare = 0;
			while (true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if (srcSquare == -1)
					break;

				String encodedSrcSquare = encodedSquares[srcSquare];
				String direction = null;
				int minDistance = -1;
				for (int i = 0; i < 8; i++) {
					if (exitMoves[playerColor.ordinal()][srcSquare][i] != -1) {
						minDistance = exitMoves[playerColor.ordinal()][srcSquare][i];
						direction = Direction.getDirectionByInt(i).toString();

						for (int distance = minDistance; playerStackSize - distance >= -1
								&& (direction.equals("N") || direction.equals("S")); distance += 2) {
							validMoves.add(BoardStateBuilder.calculateExitMove(playerPieces, enemyPieces,
									playerStackSize, srcSquare, encodedSrcSquare, direction, distance, playerColor, enemyColor));
						}

						for (int distance = minDistance; playerStackSize - distance >= -1
								&& (direction.equals("NW") || direction.equals("NE") || direction.equals("SW")
										|| direction.equals("SE")); distance += 1) {
							validMoves.add(BoardStateBuilder.calculateExitMove(playerPieces, enemyPieces,
									playerStackSize, srcSquare, encodedSrcSquare, direction, distance, playerColor,
									enemyColor));
						}
					}
				}
				srcSquare++;
			}
		}
	}

	public RepresentationNode calculateEmptyMove(BitboardRepresentationNode configuration, Color playingColor) {

		RepresentationNode emptyMove = configuration;
		BitSet[] playerPieces = configuration.getPlayerPieces(playingColor);
		boolean exit = false;

		for (int playerStackSize = 0; playerStackSize < 12; playerStackSize++) {

			int srcSquare = 0;
			while (true) {
				srcSquare = playerPieces[playerStackSize].nextSetBit(srcSquare);
				if (srcSquare == -1)
					break;
				String encodedSrcSquare = encodedSquares[srcSquare];
				String encodedMove = encodedSrcSquare.concat(",");
				if (playingColor == Color.WHITE)
					encodedMove = encodedMove.concat("N").concat(",");
				else
					encodedMove = encodedMove.concat("S").concat(",");
				encodedMove = encodedMove.concat("0");
				emptyMove.setMove(encodedMove);
				exit = true;
				break;
			}
			if (exit)
				break;
		}

		if (!exit)
			emptyMove = null;

		return emptyMove;
	}
}
