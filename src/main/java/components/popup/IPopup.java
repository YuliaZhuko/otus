package components.popup;

public interface IPopup<T> {

  T popupShouldNotBeVisible();

  T popupShouldBeVisible();
}
