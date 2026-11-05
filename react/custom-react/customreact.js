function customRender(element, container) {
  // const domElement = document.createElement(reactElement.type)
  // domElement.innerHTML = reactElement.children
  // domElement.setAttribute('href', reactElement.props.href)
  // domElement.setAttribute('target', reactElement.props.target)
  // container.appendChild(domElement)

  const domElement = document.createElement(element.type);
  domElement.innerHTML = element.children;
  for (const prop in reactElement.props) {
    domElement.setAttribute(prop, reactElement.props[prop]);
  }
  container.appendChild(domElement);
}
const reactElement = {
  type: "a",
  props: {
    href: "https://google.com",
    target: "_blank",
    style: "color: white",
  },
  children: "Click here",
};

const mainContainer = document.querySelector("#root");
customRender(reactElement, mainContainer);
