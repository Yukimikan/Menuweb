package common;

/**
 * MenuWebService
 * 共通化インターフェース.
 *
 * @param <F> Form
 * @param <R> Result
 */
public interface MenuWebService<F, R> {

  R execute(F form);
}
