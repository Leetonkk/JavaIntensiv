package homework.task1.currency.dataReaders.converters;

import com.opencsv.bean.AbstractBeanField;

import java.math.BigDecimal;

public class StringToBigDecimal extends AbstractBeanField {
    @Override
    protected Object convert(String s) {
        return new BigDecimal(s.replace(',', '.'));
    }
}
