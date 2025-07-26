package data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum OtusTypeSubscription {
  TRIAL("Trial"),
  STANDARD("Standard"),
  PROFESSIONAL("Professional");

  private final String displayName;

  OtusTypeSubscription(String displayName) {
    this.displayName = displayName;
  }

  public static List<String> getAllDisplayNames() {
    return Arrays.stream(values())
        .map(OtusTypeSubscription::getDisplayName)
        .collect(Collectors.toList());
  }

  public String getDisplayName() {
    return displayName;
  }
}

