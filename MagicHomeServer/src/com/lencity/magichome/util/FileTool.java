package com.lencity.magichome.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class FileTool {
	
	private static int i;

	public static boolean copyFile(String resourceFimeName,
			String targetFileName) throws IOException {

		return copyFile(new File(resourceFimeName), new File(targetFileName));
	}

	/**
	 * 文件拷貝
	 * 
	 * @param resourceFimeName
	 *            源文件的路徑名稱
	 * @param targetFile
	 *            目的文件
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(String resourceFimeName, File targetFile)
			throws IOException {
		return copyFile(new File(resourceFimeName), targetFile);
	}

	/**
	 * 文件拷貝
	 * 
	 * @param resourceFile
	 *            源文件
	 * @param targetFileName
	 *            目的文件的路徑名稱
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(File resourceFile, String targetFileName)
			throws IOException {
		return copyFile(resourceFile, new File(targetFileName));
	}

	/**
	 * 文件拷貝
	 * 
	 * @param resourceFile
	 *            源文件
	 * @param targetFile
	 *            目的文件
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(File resourceFile, File targetFile)
			throws IOException {
		if (resourceFile == null || targetFile == null)
			return false;

		if (resourceFile.exists()) {
			if (!targetFile.exists()) {
				File parentFile = targetFile.getParentFile();
				if (!parentFile.exists())
					parentFile.mkdirs();
				targetFile.createNewFile();
			}
			FileInputStream in = new FileInputStream(resourceFile);
			FileOutputStream out = new FileOutputStream(targetFile);
			byte[] buffer = new byte[1024 * 8];
			int i = 0;

			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.flush();
			return true;

		} else {
			return false;
		}
	}

	/**
	 * 文件刪除，
	 * 
	 * @param fileName
	 *            文件的路徑
	 */
	public static void deleteFile(String fileName) {
		if (fileName != null) {
			deleteFile(new File(fileName));
		}
	}

	/**
	 * 文件刪除
	 * 
	 * @param file
	 *            文件
	 */
	public static void deleteFile(File file) {
		if (file != null && file.exists()) {
			System.out.println(file.delete());
		}
	}

	/**
	 * 按照當前時間命名上傳的文件名
	 */
	public static String getFileNameByTime() {
		Calendar date = Calendar.getInstance(Locale.CHINESE);
		StringBuffer fileName = new StringBuffer();
		if (i == 100)
			i = 0;
		if (i <= 9) {

			fileName.append(date.get(Calendar.YEAR))
					.append(date.get(Calendar.MONTH) + 1)
					.append(date.get(Calendar.DAY_OF_MONTH))
					.append(date.get(Calendar.HOUR_OF_DAY))
					.append(date.get(Calendar.MINUTE))
					.append(date.get(Calendar.MILLISECOND)).append("00")
					.append(i);
		} else {
			fileName.append(date.get(Calendar.YEAR))
					.append(date.get(Calendar.MONTH) + 1)
					.append(date.get(Calendar.DAY_OF_MONTH))
					.append(date.get(Calendar.HOUR_OF_DAY))
					.append(date.get(Calendar.MINUTE))
					.append(date.get(Calendar.MILLISECOND)).append("0")
					.append(i);
		}

		System.out.println(date.toString());
		return fileName.toString();

	}

}
