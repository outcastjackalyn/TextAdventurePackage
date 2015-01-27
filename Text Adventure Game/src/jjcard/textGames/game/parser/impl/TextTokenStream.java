package jjcard.textGames.game.parser.impl;

import java.util.LinkedList;
import java.util.List;

import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.TextParserError;
import jjcard.textGames.game.parser.TextToken;
import static jjcard.textGames.game.util.ObjectsUtil.notEqual;

/**
 * 
 * A class to hold to result from the parser in a way easy for the game to use.
 * 
 * @param <T>
 */
public class TextTokenStream<T extends ITextTokenType> implements ITextTokenStream<T>{

	private final List<TextToken<T>> objects;

	private final TextToken<T> verb;

	private final TextToken<T> withObject;

	private final List<TextParserError> errors;

	public static class TextTokenStreamBuilder<T extends ITextTokenType> {

		private List<TextToken<T>> objects = new LinkedList<TextToken<T>>();
		private TextToken<T> verb;
		private TextToken<T> withObject;
		private List<TextParserError> errors = new LinkedList<TextParserError>();
		private boolean validateInput = true;

		public TextTokenStreamBuilder() {
			super();
		}

		/**
		 * Creates a TextTokenStreamBuilder with the objects, verb, withObject,
		 * and errors of the given stream.
		 * 
		 * @param stream
		 */
		public TextTokenStreamBuilder(TextTokenStream<T> stream) {
			this.objects = stream.objects == null ? this.objects
					: stream.objects;
			this.verb = stream.verb;
			this.withObject = stream.withObject;
			this.errors = stream.errors == null ? this.errors : stream.errors;
		}

		/**
		 * Sets the verb to the TextToken given.
		 * 
		 * @param verb
		 * @return
		 */
		public TextTokenStreamBuilder<T> verb(TextToken<T> verb) {
			this.verb = verb;
			return this;
		}

		/**
		 * Sets the withObject to the TextToken given.
		 * 
		 * @param withObject
		 * @return
		 */
		public TextTokenStreamBuilder<T> withObject(TextToken<T> withObject) {
			this.withObject = withObject;
			return this;
		}

		/**
		 * Sets the object list to the one given. If null, creates a new list.
		 * 
		 * @param objects
		 * @return
		 */
		public TextTokenStreamBuilder<T> objects(List<TextToken<T>> objects) {
			if (objects == null) {
				objects = new LinkedList<TextToken<T>>();
			}
			this.objects = objects;
			return this;
		}

		public TextTokenStreamBuilder<T> addObject(TextToken<T> object) {
			objects.add(object);
			return this;
		}

		/**
		 * Adds the given list of objects to the end of the object list
		 * 
		 * @param objects
		 * @return
		 */
		public TextTokenStreamBuilder<T> addObjects(List<TextToken<T>> objects) {
			if (objects != null) {
				this.objects.addAll(objects);
			}
			return this;
		}

		public TextTokenStreamBuilder<T> errors(List<TextParserError> errors) {
			if (errors == null) {
				errors = new LinkedList<TextParserError>();
			}
			this.errors = errors;
			return this;
		}

		/**
		 * Adds error to builder only if error not already exists in the list.
		 * 
		 * @param error
		 * @return
		 */
		public TextTokenStreamBuilder<T> addError(TextParserError error) {
			if (!this.errors.contains(error)) {
				this.errors.add(error);
			}
			return this;
		}

		/**
		 * Sets the flag to validate the input and add errors to the stream
		 * automatically. It is true by default
		 * 
		 * @param validateInput
		 * @return
		 */
		public TextTokenStreamBuilder<T> validateInput(boolean validateInput) {
			this.validateInput = validateInput;
			return this;
		}

		/**
		 * Builds a TextTokenStream with the values in the builder.
		 * 
		 * @return
		 */
		public TextTokenStream<T> build() {
			return new TextTokenStream<>(verb, objects, withObject, errors,
					validateInput);
		}
	}

	private TextTokenStream(TextToken<T> verb, List<TextToken<T>> objects,
			TextToken<T> withObject, List<TextParserError> errors,
			boolean validateInput) {

		this.objects = objects;
		this.verb = verb;
		this.withObject = withObject;
		this.errors = errors;

		if (validateInput) {
			validateInput();
		}
	}

	private void validateInput() {
		if (objects.isEmpty() && !containsError(TextParserError.NO_OBJECTS)) {
			errors.add(TextParserError.NO_OBJECTS);
		}
		if (verb == null && !containsError(TextParserError.NO_VERB)) {
			errors.add(TextParserError.NO_VERB);
		}
	}

	/**
	 * Returns true if the TextTokenStream has a object token in the withObject
	 * field.
	 * 
	 * @return
	 */
	public boolean hasWithObject() {
		return withObject != null;
	}

	/**
	 * Returns the list of Object TextTokens
	 * 
	 * @return
	 */
	public List<TextToken<T>> getObjects() {
		return objects;
	}

	/**
	 * Returns the verb TextToken
	 * 
	 * @return
	 */
	public TextToken<T> getVerb() {
		return verb;
	}

	/**
	 * Returns the withObject TextToken
	 * 
	 * @return
	 */
	public TextToken<T> getWithObject() {
		return withObject;
	}

	/**
	 * returns true if there is an object token in the objects list.
	 * 
	 * @return
	 */
	public boolean hasObject() {
		return !objects.isEmpty();
	}

	/**
	 * Returns the first Object token if the list if there is one, null
	 * otherwise.
	 * 
	 * @return
	 */
	public TextToken<T> getFirstObject() {
		return hasObject() ? objects.get(0) : null;
	}

	/**
	 * Returns the error list.
	 * 
	 * @return
	 */
	public List<TextParserError> getErrors() {
		return errors;
	}

	/**
	 * Returns true if the error list contains the given error
	 * 
	 * @param e
	 * @return
	 */
	public boolean containsError(TextParserError e) {
		return errors.contains(e);
	}

	/**
	 * Returns true of the stream has any errors
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	public String toString(){
		return "verb=" + verb + ", objects=" + objects + ", errors=" + errors;
	}
	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		if (o instanceof TextTokenStream){
			@SuppressWarnings("rawtypes")
			TextTokenStream s = (TextTokenStream) o;
			
			if (notEqual(verb, s.verb)){
				return false;
			}

			if (!objects.equals(s.objects)){
				return false;
			}
		
			if (notEqual(withObject, s.withObject)){
				return false;
			}

			if (notEqual(errors, s.errors)){
				return false;
			}
			//we're good people
			return true;

		} else {
			return false;
		}
	}

}
