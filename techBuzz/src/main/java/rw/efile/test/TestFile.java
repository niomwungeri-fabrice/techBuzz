package rw.efile.test;

import java.io.File;

public class TestFile {
	public static String FILE_PATH = "/rw/efile/test/Systemanalysisanddesign.pdf";

	public static void main(String[] args) {

		try {
			File file = new File(FILE_PATH);
			String name = file.getName();
			System.out.println(name.substring(name.lastIndexOf(".") + 1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
