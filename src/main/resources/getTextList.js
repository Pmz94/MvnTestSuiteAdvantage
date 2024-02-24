function getTextList(type, locator) {
	let elementList = [];
	if(type === 'cssSelector') {
		elementList = document.querySelectorAll(locator);
	} else if(type === 'xpath') {
		let tempElementList = document.evaluate(locator, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
		for(let i = 0; i < tempElementList.snapshotLength; i++) {
			elementList.push(tempElementList.snapshotItem(i));
		}
	}
	let returnList = [];
	for(let i = 0, length = elementList.length; i < length; ++i) {
		let element = elementList[i];
		returnList.push(getRecursiveText(element));
	}
	return returnList;
}

function getRecursiveText(node) {
	let value = getText(node);
	if(!value) {
		let children = node.children;
		let len = children.length;
		if(children && len && len > 0) {
			for(let i = 0; i < len; i++) {
				let child = children[i];
				value = getRecursiveText(child);
				if(value) break;
			}
		}
	}
	return value;
}

function getText(node) {
	let value = '';
	let emptyValueAndNode = !value && node;
	let tag = node.tagName;
	if(emptyValueAndNode && tag === 'INPUT' && node.type === 'text' && !node.readonly && node.value) {
		value = node.value;
	} else if(emptyValueAndNode && tag === 'SELECT' && node.selectedOptions && node.selectedOptions[0] && node.selectedOptions[0].innerText) {
		value = node.selectedOptions[0].innerText;
	} else if(emptyValueAndNode && tag === 'DIV' && node.firstChild && node.firstChild.nodeValue) {
		value = node.firstChild.nodeValue;
	} else if(emptyValueAndNode && tag !== 'SCRIPT' && node.innerText) {
		value = node.innerText;
	}
	return value;
}