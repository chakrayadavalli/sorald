package sorald.processor;

import org.sonar.java.checks.serialization.SerializableFieldInSerializableClassCheck;
import sorald.ProcessorAnnotation;
import sorald.FileTreeAlgorithm.Node;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.ModifierKind;

import java.util.List;

@ProcessorAnnotation(key = 1948, description = "Fields in a \"Serializable\" class should either be transient or serializable")
public class SerializableFieldInSerializableClassProcessor extends SoraldAbstractProcessor<CtField> {

	public SerializableFieldInSerializableClassProcessor(String originalFilesPath) {
		super(originalFilesPath, new SerializableFieldInSerializableClassCheck());
	}

	public SerializableFieldInSerializableClassProcessor(List<Node> segment) {
        super(segment, new SerializableFieldInSerializableClassCheck());
    }

	@Override
	public boolean isToBeProcessed(CtField element) {
		if (!super.isToBeProcessedAccordingToStandards(element)) {
			return false;
		}
		return true;
	}

	@Override
	public void process(CtField element) {
		super.process(element);
		element.addModifier(ModifierKind.TRANSIENT);
	}

}
