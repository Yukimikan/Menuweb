package common;

/**
 * MenuWebService
 * 共通化インターフェース
 *
 * @param <F>
 * @param <R>
 */
public interface MenuWebService<F, R> {

    R execute(F form);
}
