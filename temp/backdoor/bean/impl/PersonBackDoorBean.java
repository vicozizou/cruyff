package com.bytepoxic.backdoor.bean.impl;

import java.text.ParseException;
import java.util.Date;

import com.aureabox.backdoor.bean.AbstractBackDoor;
import com.aureabox.backdoor.throwing.BackDoorException;
import com.aureabox.webcore.model.Gender;
import com.aureabox.webcore.model.IdentificationType;
import com.aureabox.webcore.model.Nationality;
import com.aureabox.webcore.model.Person;
import com.aureabox.webcore.model.Place;

public class PersonBackDoorBean extends AbstractBackDoor {
	@Override
	public void parseValues(String[] values, Object target) {
		// birthday, names, gender, identification, identification_type,
		// surnames, home_place, nationality, work_place
		Person person = (Person) target;
		int i = 0;

		try {
			person.setBirthday(DATE_FORMAT.parse(values[i++]));
		} catch (ParseException e) {
			logger.error(String.format("Wrong birthdate %s, ignoring", values[i - 1]), e);
			return;
		}

		person.setNames(values[i++]);
		person.setGender(Gender.MALE.getGender().equalsIgnoreCase(values[i++]) ? Gender.MALE : Gender.FEMALE);
		person.setIdentification(values[i++]);
		person.setIdentificationType(resolveIdType(values[i++]));
		person.setSurnames(values[i++]);
		person.setDeleted(Boolean.parseBoolean(values[i++]));
		person.setHomePlace(Place.findPlace(convertId(values[i++])));
		person.setNationality(Nationality.findNationality(convertId(values[i++])));
		person.setWorkPlace(Place.findPlace(convertId(values[i++])));
		person.setDeleted(false);
		person.setCreationDate(new Date());
	}

	private IdentificationType resolveIdType(String value) {
		if (IdentificationType.LOCALE.getType().equalsIgnoreCase(value)) {
			return IdentificationType.LOCALE;
		} else if (IdentificationType.RESIDENCE.getType().equalsIgnoreCase(value)) {
			return IdentificationType.RESIDENCE;
		} else if (IdentificationType.AMNESTY.getType().equalsIgnoreCase(value)) {
			return IdentificationType.AMNESTY;
		} else if (IdentificationType.PASSPORT.getType().equalsIgnoreCase(value)) {
			return IdentificationType.PASSPORT;
		} else {
			return IdentificationType.OTHER;
		}
	}

	@Override
	public int getFieldsCount() {
		return 10;
	}

	@Override
	public void processLine(String[] values) throws BackDoorException {
	}

	@Override
	public void handleComment(String line) {
	}
}
