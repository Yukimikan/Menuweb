package common;

/*
 *
 */
public enum ConditionTotal {
	  以上 {
	    @Override
	    public boolean match(int price, int total) {
	      return price >= total;
	    }
	  },
	  以下 {
	    @Override
	    public boolean match(int price, int total) {
	      return price <= total;
	    }
	  },
	  等しい {
	    @Override
	    public boolean match(int price, int total) {
	      return price == total;
	    }
	  },
	  未満 {
	    @Override
	    public boolean match(int price, int total) {
	      return price < total;
	    }
	  };

	public static ConditionTotal fromCode(String code) {
	  return switch(code) {
	    case "1" -> 以上;
	    case "2" -> 以下;
	    case "3" -> 等しい;
	    case "4" -> 未満;
	    default -> throw new IllegalArgumentException("不正な条件コード");
	  };
	}

	  public abstract boolean match(int price, int total);
}