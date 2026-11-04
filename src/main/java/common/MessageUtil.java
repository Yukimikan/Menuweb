package common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class MessageUtil {

//	@Autowired
//	private MessageResource messageResource;
    private static final Properties properties;

    private MessageUtil() throws Exception {
    }

//  try {
//    properties.load(Files.newBufferedReader(Paths.get(INIT_FILE_PATH), StandardCharsets.UTF_8));
//  } catch (IOException e) {
//    System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", INIT_FILE_PATH));
//  }
    static {
        properties = new Properties();
        try (InputStream is =
            MessageUtil.class.getClassLoader()
                                 .getResourceAsStream("message.properties");
             Reader reader =
             new InputStreamReader(is, StandardCharsets.UTF_8)
        	) {
            properties.load(reader);

        } catch (IOException e) {
        	// ファイル読み込みに失敗
            e.printStackTrace();
        }
    }

    /**
     * プロパティ値を取得する
     *
     * @param key キー
     * @return 値
     */
    public static String getProperty(final String key) {
        return getProperty(key, "");
    }

    /**
     * プロパティ値を取得する
     *
     * @param key キー
     * @param defaultValue デフォルト値
     * @return キーが存在しない場合、デフォルト値
     *          存在する場合、値
     */
    public static String getProperty(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
